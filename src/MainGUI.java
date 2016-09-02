/**
 * Created by corpi on 2016-07-09.
 */

import com.sun.jna.platform.unix.X11;
import general.TestChatBotMain;
import general.graph.theory.WikipediaInfoBoxModel2OldJune14_PERSONAL;
import javafx.stage.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {

    String      appName     = "Colt Chat v0.1";
    MainGUI     mainGUI;
    JFrame      newFrame    = new JFrame(appName);
    JButton     sendMessage;
    JTextField  messageBox;
    JTextArea   chatBox;
    JTextField  usernameChooser;
    JFrame      preFrame;
    String      chatbotName = "Petricia";
    int         size        = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.7);
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager
                            .getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                MainGUI mainGUI = new MainGUI();


                mainGUI.dataChooser(mainGUI);
            }
        });
    }
    public void dataChooser(MainGUI mainGUI)
    {
        JFrame currFrame = new JFrame(appName);
        currFrame.setLayout(new GridLayout(1,3));
        Button button1 = new Button("Personal Bot");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WikipediaInfoBoxModel2OldJune14_PERSONAL.changeStatementsFileName("statements_july6.txt");
                currFrame.setVisible(false);
                mainGUI.preDisplay();

            }
        } );
        currFrame.add(button1);
        Button button2 = new Button("Web Q & A");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WikipediaInfoBoxModel2OldJune14_PERSONAL.changeStatementsFileName("statements");
                currFrame.setVisible(false);
                mainGUI.preDisplay();
            }
        } );
        currFrame.add(button2);
        Button button3 = new Button("Local Q & A");
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WikipediaInfoBoxModel2OldJune14_PERSONAL.changeStatementsFileName("statements_selection");
                currFrame.setVisible(false);
                mainGUI.preDisplay();
            }
        } );
        currFrame.add(button3);
        currFrame.setSize(size, size);
        currFrame.setVisible(true);
    }
    public void preDisplay() {
        newFrame.setVisible(false);
        preFrame = new JFrame(appName);
        usernameChooser = new JTextField(15);
        JLabel chooseUsernameLabel = new JLabel("Pick a username:");
        JButton enterServer = new JButton("Enter Chat Server");
        enterServer.addActionListener(new enterServerButtonListener());
        JPanel prePanel = new JPanel(new GridBagLayout());

        GridBagConstraints preRight = new GridBagConstraints();
        preRight.insets = new Insets(0, 0, 0, 10);
        preRight.anchor = GridBagConstraints.EAST;
        GridBagConstraints preLeft = new GridBagConstraints();
        preLeft.anchor = GridBagConstraints.WEST;
        preLeft.insets = new Insets(0, 10, 0, 10);
        // preRight.weightx = 2.0;
        preRight.fill = GridBagConstraints.HORIZONTAL;
        preRight.gridwidth = GridBagConstraints.REMAINDER;

        prePanel.add(chooseUsernameLabel, preLeft);
        prePanel.add(usernameChooser, preRight);
        preFrame.add(BorderLayout.CENTER, prePanel);
        preFrame.add(BorderLayout.SOUTH, enterServer);
        preFrame.setSize(size, size);
        preFrame.setVisible(true);

    }

    public void display() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.BLUE);
        southPanel.setLayout(new GridBagLayout());

        messageBox = new JTextField(30);
        messageBox.requestFocusInWindow();

        sendMessage = new JButton("Send Message");
        sendMessage.addActionListener(new sendMessageButtonListener());

        chatBox = new JTextArea();
        chatBox.setEditable(false);
        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
        chatBox.setLineWrap(true);

        mainPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);

        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0D;
        left.weighty = 1.0D;

        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(0, 10, 0, 0);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;

        southPanel.add(messageBox, left);
        southPanel.add(sendMessage, right);

        mainPanel.add(BorderLayout.SOUTH, southPanel);

        newFrame.add(mainPanel);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(size, size);
        newFrame.setVisible(true);
    }
    public void setSendErrorMessageToUser(String errorMessage) throws Exception {
        chatBox.append("<" + chatbotName + ">:  " +  errorMessage
                + "\n");
    }
    public void setSendMessageToUser(String userResponse) throws Exception {
        if(WikipediaInfoBoxModel2OldJune14_PERSONAL.statementsFileName.equals(
                WikipediaInfoBoxModel2OldJune14_PERSONAL.statementsDirectoryName
                +"statements_july6.txt"))
        chatBox.append("<" + chatbotName + ">:  " +  MainApp.dostuff("GET",userResponse,
                0)
                + "\n");
        else if(WikipediaInfoBoxModel2OldJune14_PERSONAL.statementsFileName.equals(WikipediaInfoBoxModel2OldJune14_PERSONAL.statementsDirectoryName
                +"statements"))
            chatBox.append("<" + chatbotName + ">:  " +  MainApp.dostuff("GET",userResponse,
                    1)
                    + "\n");
        else if(WikipediaInfoBoxModel2OldJune14_PERSONAL.statementsFileName.equals(WikipediaInfoBoxModel2OldJune14_PERSONAL.statementsDirectoryName
                +"statements_selection"))
            chatBox.append("<" + chatbotName + ">:  " +  MainApp.dostuff("GET",userResponse,
                    2)
                    + "\n");
    }
    class sendMessageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (messageBox.getText().length() < 1) {
                // do nothing
                messageBox.requestFocusInWindow();
            } else if (messageBox.getText().equals(".clear")) {
                chatBox.setText("Cleared all messages\n");
                messageBox.setText("");
                messageBox.requestFocusInWindow();
            } else {
                String question = messageBox.getText();
                chatBox.append("<" + username + ">:  " + messageBox.getText()
                        + "\n");

                messageBox.setText("");
                messageBox.requestFocusInWindow();
                try {
                    setSendMessageToUser(question);
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        setSendErrorMessageToUser("... Error...");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }


        }
    }

    String  username;

    class enterServerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            username = usernameChooser.getText();
            if (username.length() < 1) {
                System.out.println("No!");
            } else {
                preFrame.setVisible(false);
                display();
            }
        }

    }
}