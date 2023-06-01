import java.lang.String;
import java.io.*;
import java.util.*;

class passwordChecker {


	public static void printStrongNess(String input)
	{
		// Checking lower alphabet in string
		int n = input.length();
		boolean hasLower = false, hasUpper = false,
				hasDigit = false, specialChar = false;
		Set<Character> set = new HashSet<Character>(
			Arrays.asList('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+'));
		for (char i : input.toCharArray())
		{
			if (Character.isLowerCase(i))
				hasLower = true;
			if (Character.isUpperCase(i))
				hasUpper = true;
			if (Character.isDigit(i))
				hasDigit = true;
			if (set.contains(i))
				specialChar = true;
		}
	
		// Strength of password
		System.out.print("Strength of password:- ");
		if (hasDigit && hasLower && hasUpper && specialChar
			&& (n >= 8))
			System.out.print(" Strong");
		else if ((hasLower || hasUpper || specialChar)
				&& (n >= 6))
			System.out.print(" Moderate");
		else
			System.out.print(" Weak");
	}
    public static String EncryptInFile(String str, int key, int flag)
    {
        String result="";
        for (int i=0; i<str.length(); i++)
        {
            if (Character.isUpperCase(str.charAt(i)))
            {
                char ch = (char)(((int)str.charAt(i) + key - 65) % 26 + 65);
                result=result+ch;
            }
            else if(Character.isLowerCase(str.charAt(i)))
            {
                char ch = (char)(((int)str.charAt(i) + key - 97) % 26 + 97);
                result=result+ch;
            }
            else if(Character.isDigit(str.charAt(i)))
            {
                char ch = (char)(((int)str.charAt(i) + key - 32) % 26 + 32);
                result=result+ch;
            }
            else
            {
                // System.out.println((int)str.charAt(i));
                char ch = (char)(((int)str.charAt(i) + key - 32) % 26 + 32);
                result=result+ch;
                // System.out.println((int)ch);
            }
        }
        System.out.println("Result: "+result);
        if(flag==0)
        {
        try{
            FileWriter fw= new FileWriter("C:\\Cyber Project\\PasswordVault.txt");
            fw.write(result);
            fw.close();
            System.out.println("Succesfully Wrote in the File: PasswordVault.txt");
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
        }
        return result;
    }
    public static String MonoalphabaticSubEncrypt(String str, int key)
    {
        StringBuffer string = new StringBuffer(str);
        char normalchar[]={ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i','j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
        's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F','G','H','I','J','K','L','M','N','O','P','Q',
        'R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9','~','!','@','#','$','%','^','&','*',
        '(',')','_','-','+','='};

        char codedchar[]={ 'Q', '@', 'E', 'R', 'T', '1', 'U', '9', 'O','P', 'u', 'S', 'D', 'F', 'G', 'H', 'J', 'K',
        'L', 'Z', 'o', 'C', 'V', 'B', 'N', '3','a','d','e','f','h','b','7','k','X','p','l','w','q','%','v','n','~','c',
        's','g','j','i','A','y','t','r','2','4','z','0','Y','M','5','6','8','I','!','*','&','m','#','x','W','$','^'};
        int len=normalchar.length;
        for(int j=0;j<str.length();j++)
        {
            int i=0;
            while(normalchar[i]!='=')
            {
                if(normalchar[i]==str.charAt(j))
                string.setCharAt(j, codedchar[i]);
                i++;
            }
        }
        System.out.println("Encrypted String: "+string);
        return string.toString();
    }
    public static void  MonoalphabaticSubDecrypt(String str)
    {
        StringBuffer string = new StringBuffer(str);
        char normalchar[]={ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
        'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
        's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F','G','H','I','J','K','L','M','N','O','P','Q',
        'R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9','~','!','@','#','$','%','^','&','*',
        
        '(',')','_','-','+','='};
        char codedchar[]={ 'Q', '@', 'E', 'R', 'T', '1', 'U', '9', 'O','P', 'u', 'S', 'D', 'F', 'G', 'H', 'J', 'K',
        'L', 'Z', 'o', 'C', 'V', 'B', 'N', '3','a','d','e','f','h','b','7','k','X','p','l','w','q','%','v','n','~','c',
        's','g','j','i','A','y','t','r','2','4','z','0','Y','M','5','6','8','I','!','*','&','m','#','x','W','$','^'};
        int len=codedchar.length;
        for(int j=0;j<str.length();j++)
        {
            int i=0;
            while(i<len)
            {
                if(codedchar[i]==str.charAt(j))
                string.setCharAt(j, normalchar[i]);
                i++;
            }
        }
        System.out.println("Decrypted String: "+string);
    }

    public static String DecryptInFile()
    {
        String data="";
        try {
            File file = new File("PasswordVault.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }
	public static void main(String[] args)
	{
        Scanner sc=new Scanner(System.in);
        passwordChecker obj=new passwordChecker();
        int ch,key;
        String dummy,input;
        System.out.print("************************************     ");
        System.out.print("Welcome to the Password Vault Encoder");
        System.out.println("    ************************************");
        System.out.println("\n"+"\n");
        do
        {

            System.out.println("\n\n\n1Press 1 for password strength checking.");
            System.out.println("Press 2 for store your password using Encryption.");
            System.out.println("Press 3 to view your stored password using Decryption Algorithm.");
            System.out.println("Press 0 to exit.....");
            ch = sc.nextInt();
            switch(ch)
            {
                case 1:
                    System.out.print("Enter your Password: ");
                    dummy=sc.nextLine();
                    input = sc.nextLine();
                    obj.printStrongNess(input);
                    break;

                case 2:
                    System.out.print("Enter your Password: ");
                    dummy=sc.nextLine();
                    input = sc.nextLine();
                    key = (int)Math.floor(Math.random()*(7-2+1)+2);
                    System.out.println("Your Key is: "+ key );
                    input= obj.MonoalphabaticSubEncrypt(input, key);
                    input = obj.EncryptInFile(input, key , 0);
                    break;

                case 3:
                    System.out.println("Enter your Key to Decrypt your Password: ");
                    key=sc.nextInt();
                    String str=obj.DecryptInFile();
                    str = obj.EncryptInFile(str, 26-key, 1);
                    obj.MonoalphabaticSubDecrypt(str);
                    break;

                default:
                    if(ch!=0)
                    System.out.println("Invalid choice....");
            }
        }while(ch!=0);
	}

	
}