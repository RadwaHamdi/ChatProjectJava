/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaprojectcleintside;

import common.*;
import demo.Configuration;
import demo.ObjectFactory;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileView;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Alaa
 */
public class Controller implements Serializable {
    static String  serverIp;
    CleintModelInterface model;
    CleintVeiwInterface veiw;
    MainFrame veiwRef;
    Vector<user> contactlist;
    Vector<Chat> chats = new Vector<Chat>();
    ServicesInterface obj;
    user newcleint;
    SignIn signInView;
    SignUp signUpView;
    Registry reg;
    boolean serverstate = true;
    static boolean startserver=false;

    CleintModelInterfaceImplementation userref;

    HashMap<Integer, ArrayList<String>> sessions;
    HashMap<Integer,ArrayList<String[]>> history;
    public Controller(String ip) throws RemoteException {
        
       serverIp=ip;
        
       
        
      
        JAXBContext context;
        try {

            context = JAXBContext.newInstance("demo");
            Unmarshaller unmarsh = context.createUnmarshaller();
            Configuration config = (Configuration) unmarsh.unmarshal(new File("C:\\Users\\Alaa\\Desktop\\project\\ConfigurationFile.xml"));
            System.out.println("email: " + config.getEmail());
            System.out.println("signed value: " + config.getSigned());
            if (config.getSigned() == 0) {
                reg = LocateRegistry.getRegistry(serverIp, 5005);
                obj = (ServicesInterface) reg.lookup("chatservice");
                serverstate = obj.checkServerState();
                user u = obj.getUser(config.getEmail());
                veiwRef = new MainFrame(this, obj.retreiveContactList(u), u);
                veiw = veiwRef;
                newcleint = u;
                model = new CleintModelInterfaceImplementation(this);
                sessions = new HashMap<Integer, ArrayList<String>>();
                history = new HashMap<Integer, ArrayList<String[]>>();
                userref = new CleintModelInterfaceImplementation(this);
                obj.registerCleint(userref, u.getEmail());

            } else {
                System.out.println("failed to login automatic");
                signInView = new SignIn(this);
                model = new CleintModelInterfaceImplementation(this);
                sessions = new HashMap<Integer, ArrayList<String>>();
                history = new HashMap<Integer, ArrayList<String[]>>();
                try {

                    reg = LocateRegistry.getRegistry(serverIp, 5005);
                    obj = (ServicesInterface) reg.lookup("chatservice");
                    serverstate = obj.checkServerState();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Server is off");
                    System.exit(0);
                }
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Server is off");
                    System.exit(0);
        }
      
        
//            Mymessage myMessage = (Mymessage) unmarsh.unmarshal(new File(mypackage + "intro.xml"));
        //   Mymessage message = (Mymessage)JAXBMessage.getValue();
        
        }

    public void signInClientSide() {

        int validate = 0;
        if (validateEmailSignIn(signInView.getEmailTextField().getText()) == -1) {
            validate = -1;
        }
        if (validateEmptyFieldsSignIn(signInView.getEmailTextField().getText(), signInView.getPasswordTextField().getText()) == -1) {
            validate = -1;
        }
        if (validate == 0) {
            try {
                user newUser;

                if (serverstate == true) {

                    newUser = obj.signInServerSide(signInView.getEmailTextField().getText(), signInView.getPasswordTextField().getText());
                    if (newUser == null || newUser.getEmail() == null) {
                        JOptionPane.showMessageDialog(null, "Sorry Server is off");
                        signInView.dispose();
                    } else {
                        singInHandling(newUser);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Sorry Server is off");
                    signInView.dispose();
                }
            } catch (RemoteException ex) {
                JOptionPane.showMessageDialog(null, "Sorry Server is off");
                signInView.dispose();
            }
        }
    }

    public void singInHandling(user u) {

        if (u != null) {
            try {
                JOptionPane.showMessageDialog(signInView, "sign in successfully");
                signInView.dispose();
                veiwRef = new MainFrame(this, obj.retreiveContactList(u), u);
                veiw = veiwRef;
                newcleint = u;
                userref = new CleintModelInterfaceImplementation(this);
                obj.registerCleint(userref, u.getEmail());
            } catch (RemoteException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(signInView, "user doesn't exist");
            }

        } else {

            JOptionPane.showMessageDialog(signInView, "sign in failed");

        }
    }

    public void signUpClientSide(SignUp ref) {
        signUpView = ref;

        newcleint = new user();
        newcleint.setEmail(signUpView.getEmailTextField().getText());
        newcleint.setUserName(signUpView.getUserNameTextField().getText());
        newcleint.setPassward(signUpView.getPasswordTextField().getText());
        newcleint.setFirstName(signUpView.getFirstNameTextFeild().getText());
        newcleint.setLastName(signUpView.getLastNameTextField().getText());
        //
        System.out.println(signUpView.getEmailTextField().getText());
        int check_email = validateEmail(signUpView.getEmailTextField().getText());
        int check_empty = validateEmptyFields(signUpView.getFirstNameTextFeild().getText(), signUpView.getLastNameTextField().getText(), signUpView.getUserNameTextField().getText(), signUpView.getEmailTextField().getText(), signUpView.getPasswordTextField().getText(), signUpView.getRepasswordTextField().getText());
        int check_password = validateRepassword(signUpView.getPasswordTextField().getText(), signUpView.getRepasswordTextField().getText());
        int final_check = 0;

        if (check_empty == -1) {

            final_check = -1;
        }
        if (check_email == -1) {
            final_check = -1;
        }
        if (check_password == -1) {
            final_check = -1;
        }
        if (final_check == 0) {
            try {
                boolean check = obj.checkServerState();
                if (serverstate == true) {
                    System.out.println(serverstate);
                    int inserted = obj.insertUserData(newcleint);
                    if (inserted == 0) {
                        veiwRef = new MainFrame(this, obj.retreiveContactList(newcleint), newcleint);
                        userref = new CleintModelInterfaceImplementation(this);
                        obj.registerCleint(userref, newcleint.getEmail());
                    } else {
                        JOptionPane.showMessageDialog(null, "Sorry this user is already registered");
                    }
                    signUpView.dispose();

                } else {
                    JOptionPane.showMessageDialog(null, "Sorry Server is off");
                    signUpView.dispose();
                }
            } catch (RemoteException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

   

    public int setStatus(String email, int status) {
        int inserted = 0;
        try {
            if (serverstate == true) {
                inserted = obj.setStatusServer(email, status);
            } else {
                JOptionPane.showMessageDialog(null, "Sorry Server is off");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inserted;
    }

    public void openchatwindows_controller(ArrayList<String> chatMembers, int chat_ID) {
        sessions.put(chat_ID, chatMembers);
        //remeber to add chatmembers in GUI of chat frame :)
        Chat newchatwindow = new Chat(this);
        newchatwindow.setFrameID(chat_ID);
        chats.add(newchatwindow);

    }

    public void start_chat_session(ArrayList<String> members) {
        try {
            if (serverstate == true) {
                System.out.println("user email is : "+newcleint.getEmail());
                System.out.println("chat receivers are : "+members);
                int check = obj.startNewChatSessionstr(newcleint.getEmail(), members);

                if (check == -1) {
                    JOptionPane.showMessageDialog(null, "Sorry,you canot start this chat  session");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Sorry Server is off");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToServerSide(String message, int chat_id) {
        try {

            if (serverstate == true) {
                obj.sendMessagetoserver(newcleint.getUserName() + " : " + message, chat_id);
                String []temp = new String[2];
                temp[0] = newcleint.getUserName();
                temp[1] = message;
//                if(history.get(chat_id)==null){
//                    ArrayList<String[]> t = new ArrayList<String[]>();
//                    t.add(temp);
//                    history.put(chat_id, t);
//                }
//                else{
//                    ArrayList<String[]> t = history.get(chat_id);
//                    t.add(temp);
//                    history.put(chat_id, t);
//                }
            } else {
                JOptionPane.showMessageDialog(null, "Sorry Server is off");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            //JOptionPane.showMessageDialog(null,"Sorry Server is off");

        }

    }

    public void displaymessage(String message, int chat_id) {
        for (int i = 0; i < chats.size(); i++) {
            if (chat_id == chats.get(i).getFrameID()) {
                chats.get(i).displaymessageontextarea(message);
                StringTokenizer st = new StringTokenizer(message,":");
                String[] temp = new String[2];
                temp[0] = st.nextToken();
                temp[1] = st.nextToken();
                if(history.get(chat_id)==null){
                    ArrayList<String[]> t = new ArrayList<String[]>();
                    t.add(temp);
                    history.put(chat_id, t);
                }
                else{
                    ArrayList<String[]> t = history.get(chat_id);
                    t.add(temp);
                    history.put(chat_id, t);
                }
            }
        }
    }
    public void closechatsession(int chat_id) {
        for (int i = 0; i < chats.size(); i++) {
            if (chats.get(i).getFrameID() == chat_id) {
                chats.remove(i);
                history.remove(i);
                break;
            }
        }
        try {
            obj.closechatsession(chat_id);
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, "Sorry server is off");
            chats.get(chat_id).dispose();
        }
    }

    public void unRegisterCleint() {
        try {
            if (serverstate == true) {
                obj.unRegisterCleintServerSide(newcleint, userref);
            }

        } catch (RemoteException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void signOutCleintSide() {
        try {
            if (serverstate == true) {
                obj.unRegisterCleintServerSide(newcleint, userref);
                veiwRef.dispose();
                for(int i=0;i<chats.size();i++){
                    chats.get(i).dispose();
                }
                SignIn logInAgain = new SignIn(this);

                try {
                    JAXBContext context;
                    context = JAXBContext.newInstance("demo");
                    ObjectFactory factory = new ObjectFactory();
                    Configuration conf = factory.createConfiguration();
                    conf.setEmail(newcleint.getEmail());
                    conf.setSigned(1);
                    String mypackage = "C:\\Users\\Alaa\\Desktop\\project\\Team13\\javaprojectcleintside\\src\\javaprojectcleintside\\Config_schema.xsd";
                    Marshaller marshal = context.createMarshaller();
                    marshal.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, mypackage);
                    marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                    marshal.marshal(conf, new File("C:\\Users\\Alaa\\Desktop\\project\\ConfigurationFile.xml"));
                } catch (JAXBException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }

                signInView = logInAgain;
            } else {
                JOptionPane.showMessageDialog(null, "Sorry server is off");
            }

            //signInClientSide();
        } catch (RemoteException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TellCleint_ServerIsOff() {
        serverstate = false;

    }

    public void TellCleint_ServerIson() {
        serverstate = true;

    }

    public int validateEmail(String emailTextField) {

        //String email= "Mm.9mS_s@nlicom.C_w";//put here emailTextField instead
        Pattern emailPattern = Pattern.compile("[a-z0-9._-]+@[a-z0-9.-]+\\.[a-z]{2,4}+");
        Matcher match = emailPattern.matcher(emailTextField.toLowerCase());
        boolean b = match.matches();
        System.out.println(b);
        if (b == false) {
            signUpView.dialog("Please enter a valid email");
            return -1;
        } else {
            return 0;
        }
    }

    public int validateEmptyFields(String firstNameTextField, String lastNameTextField, String userNameTextField, String passwordTextField, String repasswordTextField, String emailTextField) {
        int check = 0;

        if (firstNameTextField.equals("")) {

            signUpView.dialog("Please enter your first name");
            check = -1;
        }
        if (lastNameTextField.equals("")) {
            signUpView.dialog("Please enter your last name");
            check = -1;
        }
        if (userNameTextField.equals("")) {
            signUpView.dialog("Please enter your user name");
            check = -1;
        }
        if (passwordTextField.equals("")) {
            signUpView.dialog("Please enter your password");
            check = -1;
        }
        if (repasswordTextField.equals("")) {
            signUpView.dialog("Please enter your password again");
            check = -1;
        }
        if (emailTextField.equals("")) {
            signUpView.dialog("Please enter your email");
            check = -1;
        }
        return check;
    }

    public int validateRepassword(String passwordTextField, String repasswordTextField) {
        if (!repasswordTextField.equalsIgnoreCase(passwordTextField)) {
            signUpView.dialog("Please enter your password correctly");
            return -1;
        } else {
            return 0;
        }
    }

    public int validateEmptyFieldsSignIn(String Email, String password) {
        int check = 0;

        if (Email.equals("")) {

            signInView.dialog("Please enter your email");
            check = -1;
        }
        if (password.equals("")) {
            signInView.dialog("Please enter your passward");
            check = -1;
        }

        return check;
    }

    public int validateEmailSignIn(String emailTextField) {

        //String email= "Mm.9mS_s@nlicom.C_w";//put here emailTextField instead
        Pattern emailPattern = Pattern.compile("[a-z0-9._-]+@[a-z0-9.-]+\\.[a-z]{2,4}+");
        Matcher match = emailPattern.matcher(emailTextField.toLowerCase());
        boolean b = match.matches();
        System.out.println(b);
        if (b == false) {
            signInView.dialog("Please enter a valid email");
            return -1;
        } else {
            return 0;
        }
    }

    public void displayadvertisement(byte[] b) {

        try {
            System.out.println("receive advertisment");
            FileOutputStream fos = new FileOutputStream("C:\\Users\\Alaa\\Desktop\\project\\test2.jpg");
            fos.write(b);
            fos.close();
            System.out.println("recieved");
        } catch (Exception e) {
            e.printStackTrace();
        }
        veiwRef.showAdvertisement("C:\\Users\\Alaa\\Desktop\\project\\test2.jpg");

    }

    public void uploadFile(int frameId) {
        if(serverstate==true){
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileView(new FileView() {
                @Override
                public Icon getIcon(File f) {
                    return FileSystemView.getFileSystemView().getSystemIcon(f);
                }

            });

            fileChooser.showOpenDialog(veiwRef);
            //fileChooser.setCurrentDirectory(new File("C:\\Users\\ewess\\Desktop"));
            int fileChooserResponse = 0;

            if (fileChooserResponse == JFileChooser.APPROVE_OPTION) {
                System.out.println("upload file ");
                File file = new File(fileChooser.getSelectedFile().toURI());
                FileInputStream fis = new FileInputStream((file.getAbsolutePath()));
                int fileSize = fis.available();
                byte[] b = new byte[fileSize];
                fis.read(b);

                obj.downloadFileServerSide(file, b, frameId, newcleint.getEmail());
                System.out.println("FileReaderStream >> file size ");
                fis.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }}
        else{
            JOptionPane.showMessageDialog(null, "Sorry server is Off");
        }
    }

    public int addFriendClienSide(String userEmail, String receiverEmail) {
        int flag = 3;
        if(serverstate==true){
        try {

            flag = obj.addFriendServerSide(userEmail, receiverEmail);
        } catch (RemoteException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            // flag = 1;
        }
        }
        else{
            JOptionPane.showMessageDialog(null,"Sorry the server is off");
        }
        return flag;
    }

    public int removeFriendClientSide(String userEmail, String friendEmail) {
        int check = 2;
        if(serverstate==true){
        try {
            check = obj.removeFriendServerSide(userEmail, friendEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }}
        else{
            JOptionPane.showMessageDialog(null,"Sorry Server is off.");
        }
        return check;
    }

    public void popUpOnlineCleints(user Client) {
        veiwRef.popUpMessage(Client);

    }

    public Vector getFriendRequests(user Cleint) {
        
        Vector<user> requests = new Vector<user>();
        if(serverstate==true){
        try {
            requests = obj.retreiveFriendRequestsList(Cleint);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }else{
            JOptionPane.showMessageDialog(null, "Sorry server is off ");
        }
        return requests;
    }

    public int acceptFriendRequest(String user_email, String Sender_email) {
        int check = 0;
        if(serverstate==true){
        try {
            check = obj.acceptFriendServerSide(user_email, Sender_email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }else{
            JOptionPane.showMessageDialog(null,"Sorry Server is off");
        }
        return check;
    }

    public int denyFriendRequest(String user_email, String Sender_email) {
        int check = 0;
        if(serverstate==true){
        try {
            check = obj.denyFriendRequesServerSide(user_email, Sender_email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }else{
            JOptionPane.showMessageDialog(null, "Sorry Server is Off");
        }
        return check;
    }
    public Vector<user> getContactList(user myuser){
        Vector<user> contacts=new Vector<user>();
        if(serverstate==true){
        try {
       contacts=obj.retreiveContactList(myuser);
        } catch(Exception e){
           JOptionPane.showMessageDialog(null,"Sorry server is off ");
           System.exit(0);
        }
        }
        return contacts;
    }
     
    public void saveChat(int chat_id,String fileName) {
        FileOutputStream schemaFile = null ;
        try {
            String schema = ""
                    + "<?xml version=\"1.0\"?>\n" +
                    "<!--\n" +
                    "To change this license header, choose License Headers in Project Properties.\n" +
                    "To change this template file, choose Tools | Templates\n" +
                    "and open the template in the editor.\n" +
                    "-->\n" +
                    "\n" +
                    "<xs:schema version=\"1.0\"\n" +
                    "           xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"\n" +
                    "           elementFormDefault=\"qualified\">\n" +
                    "    <xs:element name=\"chat\">\n" +
                    "        <xs:complexType>\n" +
                    "            <xs:sequence>\n" +
                    "                <xs:element name=\"header\" type = \"simple\" default = \"Chat History\"/>  \n" +
                    "                <xs:element name=\"member\" minOccurs = \"2\" maxOccurs = \"unbounded\" type = \"xs:string\">  </xs:element>\n" +
                    "                <xs:element name = \"message\" minOccurs = \"2\" maxOccurs = \"unbounded\">\n" +
                    "                    <xs:complexType>\n" +
                    "                        <xs:sequence>\n" +
                    "                            <xs:element name = \"sender\" type = \"simple\"/>\n" +
                    "                            <xs:element name = \"body\">\n" +
                    "                                <xs:complexType>\n" +
                    "                                    <xs:simpleContent>\n" +
                    "                                        <xs:extension base=\"xs:string\">\n" +
                    "                                            <xs:attribute name=\"size\" type=\"xs:integer\" default=\"14\"/>\n" +
                    "                                            <xs:attribute name=\"type\" type=\"xs:string\" default=\"Arial\"/>\n" +
                    "                                            <xs:attribute name=\"color\" type=\"xs:string\" default=\"BLACK\"/>\n" +
                    "                                        </xs:extension>\n" +
                    "                                    </xs:simpleContent>\n" +
                    "                                    \n" +
                    "                                </xs:complexType>\n" +
                    "                            </xs:element>\n" +
                    "                        </xs:sequence>\n" +
                    "                    </xs:complexType>\n" +
                    "                </xs:element>\n" +
                    "            </xs:sequence>\n" +
                    "        </xs:complexType>\n" +
                    "        \n" +
                    "    </xs:element>\n" +
                    "    <xs:simpleType name=\"simple\">\n" +
                    "        <xs:restriction base=\"xs:string\"/> \n" +
                    "    </xs:simpleType>\n" +
                    "\n" +
                    "</xs:schema>\n" ;
            
            File schemaXSD = new File("./schemaChat.xsd");
            schemaFile = new FileOutputStream(schemaXSD);
            
            ArrayList <String[]> chatHistory = history.get(chat_id);
            try {
                JAXBContext context = JAXBContext.newInstance("generated");
                File file = new File(fileName);
                generated.ObjectFactory factory = new generated.ObjectFactory();
                generated.Chat chat = factory.createChat();
                chat.setHeader("chat");
                
                List <String> people = chat.getMember();
                ArrayList<String> chatMembers = sessions.get(chat_id);
                for (String chatmember : chatMembers) {
                    people.add(chatmember);
                }
                List <generated.Chat.Message> messages = chat.getMessage();
                for (int i = 0; i < chatHistory.size(); i++) {
                    generated.Chat.Message msg = new generated.Chat.Message() ;
                    msg.setSender(chatHistory.get(i)[0]);
                    generated.Chat.Message.Body body = new generated.Chat.Message.Body();
                    body.setValue(chatHistory.get(i)[1]);
                    msg.setBody(body);
                    messages.add(msg);
                }
                
                
                Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "src\\javaprojectclientside\\chatSchema.xsd");
            marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "./schemaChat.xsd");
            marshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders", "<?xml-stylesheet type=\"text/xsl\" href=\"src\\javaprojectcleintside\\ChatXSLT.xsl\" ?>");
            marshaller.marshal(chat, new FileOutputStream(fileName));
//            marshaller.marshal(chat, new FileOutputStream("lll.xml"));
            
        } catch (JAXBException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                schemaFile.close();
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
