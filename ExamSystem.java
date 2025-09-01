import java.util.Scanner;

public class SimpleExamSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Users data: username, password, isAdmin
        String[] usernames = {"admin"};
        String[] passwords = {"admin123"};
        boolean[] isAdmin = {true};

        // Questions data: subject, question, 4 options, correct option
        String[][] questions = new String[10][7];
        int questionCount = 0;

        System.out.println("Welcome to Online Examination System");

        boolean running = true;
        while (running) {
            System.out.println("\n1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int choice = -1;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                choice = -1;
            }

            if (choice == 1) {
                System.out.print("Username: ");
                String username = sc.nextLine();
                System.out.print("Password: ");
                String password = sc.nextLine();

                int userIndex = -1;
                for (int i = 0; i < usernames.length; i++) {
                    if (usernames[i].equals(username) && passwords[i].equals(password)) {
                        userIndex = i;
                        break;
                    }
                }

                if (userIndex == -1) {
                    System.out.println("Invalid credentials.");
                    continue;
                }

                if (isAdmin[userIndex]) {
                    // Admin menu
                    boolean adminRunning = true;
                    while (adminRunning) {
                        System.out.println("\n1. Add Question");
                        System.out.println("2. View Questions");
                        System.out.println("3. Logout");
                        System.out.print("Choose an option: ");
                        int adminChoice = -1;
                        try {
                            adminChoice = Integer.parseInt(sc.nextLine());
                        } catch (Exception e) {
                            adminChoice = -1;
                        }
                        if (adminChoice == 1) {
                            if (questionCount >= 10) {
                                System.out.println("Max question limit reached.");
                                continue;
                            }
                            System.out.print("Enter subject: ");
                            String subject = sc.nextLine();
                            System.out.print("Enter question text: ");
                            String qText = sc.nextLine();
                            for (int i = 0; i < 4; i++) {
                                System.out.print("Enter option " + (i + 1) + ": ");
                                questions[questionCount][i+1] = sc.nextLine();
                            }
                            System.out.print("Enter correct answer option number (1-4): ");
                            String correct = sc.nextLine();
                            questions[questionCount][0] = subject;
                            questions[questionCount][5] = qText;
                            questions[questionCount][6] = correct;
                            questionCount++;
                            System.out.println("Question added successfully!");
                        } else if (adminChoice == 2) {
                            if (questionCount == 0) {
                                System.out.println("No questions added yet.");
                                continue;
                            }
                            for (int i = 0; i < questionCount; i++) {
                                System.out.println("Subject: " + questions[i][0]);
                                System.out.println(questions[i][5]);
                                for (int j = 1; j <= 4; j++) {
                                    System.out.println(j + ". " + questions[i][j]);
                                }
                                System.out.println("Correct answer: " + questions[i][6]);
                                System.out.println();
                            }
                        } else if (adminChoice == 3) {
                            adminRunning = false;
                            System.out.println("Admin logged out.");
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    }
                } else {
                    // Student menu (no students exist in this simple code, so we can ignore or add later)
                    System.out.println("Student functionality not implemented.");
                }
            } else if (choice == 2) {
                running = false;
                System.out.println("Exiting system. Goodbye!");
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        sc.close();
    }
}