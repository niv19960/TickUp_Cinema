package View;

import Controller.ProgramManager;
import Model.Enums.EnumSeats;
import Model.Theaters.Theater;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// MultiDimensional theater top view Screen
public class SeatsMultiDimScreen extends MoviesFrame {
    // Fields and defines
    private static final Color screenColor = new Color(227, 213, 202);
    private static final Color availableSeat = new Color(42, 157, 143);
    private static final Color unavailableSeat = new Color(231, 111, 81);
    private static final Color selectedSeat = new Color(233, 196, 106);

    private JButton next = new JButton("Continue");
    private final int numOfTicketsNeeded;
    private int numOfTicketsSelected;
    private final Theater selectedTheater;
    private JButton[][] seats = new JButton[5][6];

    public SeatsMultiDimScreen(int numOfTickets, Theater selectedTheater) {
        // Selected theater and tickets quantity from controller
        this.numOfTicketsNeeded = numOfTickets;
        this.numOfTicketsSelected = 0;
        this.selectedTheater = selectedTheater;

        // Screen title
        JLabel title = new JLabel("Choose Seats", SwingConstants.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 30));
        title.setBounds(0, 0, 999, 60);
        title.setForeground(textColor);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.BOTTOM);

        JPanel top = new JPanel();
        top.setBounds(0, 0, 999, 80);
        top.setLayout(new BorderLayout());
        top.add(title);
        top.setBackground(backgroundColor);

        JPanel screen = new JPanel();
        screen.setBounds(200, 110, 599, 10);
        screen.setBackground(screenColor);

        JPanel spacer1 = new JPanel();
        spacer1.setBounds(0, 110, 200, 10);
        spacer1.setBackground(backgroundColor);

        JPanel spacer2 = new JPanel();
        spacer2.setBounds(799, 110, 200, 10);
        spacer2.setBackground(backgroundColor);

        JPanel spacer3 = new JPanel();
        spacer3.setBounds(0, 80, 999, 30);
        spacer3.setBackground(backgroundColor);

        // Seats statuses in the theater
        EnumSeats[][] seatsStatuses = selectedTheater.getSeats();

        // Seats buttons drawing
        final int w = 40;
        final int h = 40;

        int x = 280;
        int y = 40;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                JButton b = new JButton();
                b.setBounds(x + j * 80, y + i * 50, w, h);
                if (seatsStatuses[i][j] == EnumSeats.AVAILABLE) {
                    b.setBackground(availableSeat);
                    // Seat listener
                    b.addActionListener(new SeatButtonListener(i, j) {
                        public void actionPerformed(ActionEvent event) {
                            JButton currentButton = (JButton) event.getSource();
                            updateSeat(currentButton, this.getRow(), this.getCol());
                        }
                    });
                }
                if (seatsStatuses[i][j] == EnumSeats.TAKEN) {
                    b.setBackground(unavailableSeat);
                    b.setEnabled(false);
                }
                seats[i][j] = b;
            }
        }

        JPanel theater = new JPanel();
        theater.setBounds(0, 120, 999, 313);
        theater.setBackground(backgroundColor);
        theater.setLayout(null);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                theater.add(seats[i][j]);
            }
        }

        // Continue to next screen button
        next.setBounds(0, 433, 80, 50);
        next.setFont(new Font("Tahoma", Font.BOLD, 22));
        next.setBackground(buttonColor);
        next.setForeground(textColor);
        next.setFocusable(false);
        next.setHorizontalAlignment(SwingConstants.CENTER);
        next.setVerticalAlignment(SwingConstants.CENTER);
        next.setEnabled(false);

        JPanel bottom = new JPanel();
        bottom.setBounds(0, 433, 999, 130);
        bottom.add(next);
        bottom.setBackground(backgroundColor);

        this.add(top);
        this.add(spacer3);
        this.add(screen);
        this.add(spacer1);
        this.add(screen);
        this.add(spacer2);
        this.add(theater);
        this.add(bottom);

        // Next screen button listener
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                seatsConfirmation();
            }
        });
    }

    // Sends to the controller the theater dimension and theater itself
    private void seatsConfirmation() {
        ProgramManager.switchToOrderDetailsAndConfirmation(selectedTheater, 5, 6);
    }

    // Updates seat status and color
    private void updateSeat(JButton button, int row, int col) {
        EnumSeats seatStatus = selectedTheater.getSeats()[row][col];

        if (seatStatus == EnumSeats.AVAILABLE) {
            button.setBackground(selectedSeat);
            selectedTheater.selectSeat(row+1, col+1);
            numOfTicketsSelected++;

            if (numOfTicketsSelected == numOfTicketsNeeded) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 6; j++) {
                        if (selectedTheater.getSeats()[i][j] == EnumSeats.AVAILABLE)
                            seats[i][j].setEnabled(false);
                    }
                }
                next.setEnabled(true);
            }
        }

        if (seatStatus == EnumSeats.SELECTED) {
            button.setBackground(availableSeat);
            selectedTheater.setSeat(row+1, col+1, EnumSeats.AVAILABLE);
            numOfTicketsSelected--;
            next.setEnabled(false);

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 6; j++) {
                    if (selectedTheater.getSeats()[i][j] == EnumSeats.AVAILABLE)
                        seats[i][j].setEnabled(true);
                }
            }
        }
    }
}
