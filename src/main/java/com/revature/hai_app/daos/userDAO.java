package com.revature.hai_app.daos;

import com.revature.hai_app.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class userDAO implements CrudeDAO<User>{
    String path = "src/main/resources/database/userdata.txt";

    @Override
    public void save(User obj) {
        try {
            File file = new File(path);
            FileWriter fw = new FileWriter(file, true);
            fw.write(obj.toFileString());
            fw.close();
        } catch (IOException e){
            throw new RuntimeException("No such file exists in the directory");
        }
    }

    @Override
    public void update(User Obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public User getByID(String id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    public List<String> getAllUsernames(){
        List<String> usernames = new ArrayList();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            String userData;
            while((userData = br.readLine()) != null) {
            String[] userArr = userData.split("/"); // id. username, password, role
            String id = userArr[0];
            String username = userArr[1];
            String password = userArr[2];
            String role = userArr[3];

            usernames.add(username);
            }
        } catch(FileNotFoundException e){
            throw new RuntimeException("An error occurred when trying to access the file.");
        } catch (IOException e){
            throw new RuntimeException("An error occurred when trying to acess the file information.");
        }
        return usernames;
    }

    public User getUserbyUsernameAndPassword(String name, String pass){
        User user = new User();

        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String userData = ""; //to store userdata
            while ((userData = br.readLine()) != null){
                String[] userArr = userData.split("/"); // [id, username, password, role]
                String id = userArr[0];
                String username = userArr[1];
                String password = userArr[2];
                String email = userArr[3];
                String role = userArr[4];

                if (name.equals(username)){
                    user.setId(id);
                    user.setUsername(username);
                    user.setRole(role);

                    if (pass.equals(password)) user.setPassword(password);
                    else break;
                }  else if (pass.equals(password)) user.setPassword(password);
            }

        } catch (FileNotFoundException e){
            throw new RuntimeException("The file you're trying to access does not exist");
        } catch (IOException e){
            throw new RuntimeException("An error occurred when trying to access file information");
        }
        return user;
    }
}
