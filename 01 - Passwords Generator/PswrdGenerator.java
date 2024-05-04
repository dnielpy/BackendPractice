import java.security.SecureRandom;
import java.util.Scanner;

class Password{
    private int lenght = 8;
    private String password;

    public Password(String Password){
        password = Password;
    }
    public Password(int Lenght){
        //Generate password with Lenght;
        password = generatePassword(Lenght);
        lenght = Lenght;
    }

    //generates the password for the object with its current length.
    public String generatePassword(int len) {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }

    //returns a boolean indicating whether it is strong or not. To be strong, it must have more than 2 uppercase letters, more than 1 lowercase letter, and more than 5 numbers.
    public boolean isStrong(){
        int mayusculas = (int) password.chars().filter(Character::isUpperCase).count();
        int minusculas = (int) password.chars().filter(Character::isLowerCase).count();
        int numeros = (int) password.chars().filter(Character::isDigit).count();

        if (mayusculas >= 2 && minusculas >= 1 && numeros >= 5) {
            return true;
        }
        else {
            return false;
        }
    }

    //Get and set
    public String getPassword() {
        return password;
    }
    public int getLenght() {
        return lenght;
    }
    public void setLenght(int lenght) {
        this.lenght = lenght;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingresa el tamaño: ");
        int user_lenght = entrada.nextInt();

        Password[] passwordsObjects = new Password[user_lenght];
        boolean[] secure_of_passwords = new boolean[user_lenght];

        for (int i = 0; i < passwordsObjects.length; i++) {
            Password temp = new Password(user_lenght);
            passwordsObjects[i] = temp;
            secure_of_passwords[i] = temp.isStrong();
            String toShow =  passwordsObjects[i].getPassword() + "   " + String.valueOf(secure_of_passwords[i]);
            System.out.println(toShow);
        }
    }
}


