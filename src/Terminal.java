//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Terminal {
    Parser parser ;
    ArrayList<String> history ;
    String curr_directory ;

    public Terminal() {
        parser = new Parser();
        curr_directory = System.getProperty("user.home");
        history = new ArrayList<>();
    }

    public static void main(String[] args) {
        Terminal terminal = new Terminal();
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.print(terminal.curr_directory + ">");
            String command = scanner.nextLine();
            terminal.chooseCommandAction(command);
        }
    }
    public void echo() {
        String[] var1 = this.parser.args;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String text = var1[var3];
            System.out.print(text + " ");
        }

        System.out.println();
    }

    public void pwd() {
        File currentDir = new File(this.curr_directory);
        System.out.println(currentDir.getAbsoluteFile());
    }

    public void cd() {
        File file;
        if (Objects.equals(this.parser.args[0], "..")) {
            file = new File(this.curr_directory);
            String file_parent = file.getParent();
            if (file_parent != null) {
                this.curr_directory = file_parent;
            } else {
                System.out.println("The file doesn't have a parent directory");
            }
        } else if (this.parser.args[0] == null) {
            this.curr_directory = System.getProperty("user.home");
        } else if (this.parser.args[0].contains("/")) {
            file = new File(this.parser.args[0]);
            if (file.exists()) {
                this.curr_directory = this.parser.args[0];
            } else {
                System.out.println("The directory is not found");
            }
        } else {
            file = new File(this.curr_directory + "\\" + this.parser.args[0]);
            if (file.exists()) {
                this.curr_directory = this.curr_directory + "\\" + this.parser.args[0];
            } else {
                System.out.println("The directory is not found");
            }
        }

    }

    public void ls() {
        File currentDir = new File(this.curr_directory);
        File[] files = currentDir.listFiles();
        if (files != null) {
            Arrays.sort(files, (f1, f2) -> {
                return f1.getName().compareTo(f2.getName());
            });
            File[] var3 = files;
            int var4 = files.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                File file = var3[var5];
                System.out.print(file.getName() + "  ");
            }

            System.out.println();
        } else {
            System.out.println("The current directory has no content");
        }

    }

    public void ls_reversed() {
        File currentDir = new File(this.curr_directory);
        File[] files = currentDir.listFiles();
        if (files != null) {
            Arrays.sort(files, (f1, f2) -> {
                return f2.getName().compareTo(f1.getName());
            });
            File[] var3 = files;
            int var4 = files.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                File file = var3[var5];
                System.out.print(file.getName() + "  ");
            }

            System.out.println();
        } else {
            System.out.println("The current directory has no content");
        }

    }

    public void mkdir() {
        String[] var1 = this.parser.args;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String dir = var1[var3];
            File file;
            boolean state;
            if (dir.contains("/")) {
                file = new File(dir);
                state = file.mkdir();
                if (state) {
                    System.out.println("Directory created successfully");
                } else {
                    System.out.println(dir + " directory couldn't be created");
                }
            } else {
                dir = this.curr_directory + "\\" + dir;
                System.out.println(dir);
                file = new File(dir);
                state = file.mkdir();
                if (state) {
                    System.out.println("Directory created successfully");
                } else {
                    System.out.println(dir + " directory couldn't be created");
                }
            }
        }

    }

    public void exit_code() {
        System.out.println("Thank you for using our program");
        System.exit(0);
    }

    public void rm() {
        String s = this.curr_directory + File.separator + this.parser.args[0];
        System.out.println(s);
        File f = new File(s);
        if (f.delete()) {
            System.out.println("deleted successfully...!");
        } else {
            System.out.println("File not Found");
        }

    }

    public void cat() {
        File f = new File(this.parser.args[0]);
        if (!f.canRead()) {
            System.out.println("Wrong Path..!");
        } else {
            String data = "";

            try {
                Scanner scanner = new Scanner(f);
                data = data + scanner;
            } catch (FileNotFoundException var6) {
                var6.printStackTrace();
            }

            if (this.parser.args.length > 1) {
                new File(this.parser.args[1]);
                if (!f.canRead()) {
                    System.out.println("Wrong Path..!");
                    return;
                }

                try {
                    Scanner scanner = new Scanner(f);
                    data = data + scanner;
                } catch (FileNotFoundException var5) {
                    var5.printStackTrace();
                }
            }

            System.out.println(data);
        }
    }

    public void getHistory(){
        for (String s : history) {
            System.out.println(s);
        }
    }
    public void chooseCommandAction(String input) {
        this.parser.parse(input);
        history.add(this.parser.commandName);
        if (Objects.equals(this.parser.commandName, "exit")) {
            this.exit_code();
        } else if (Objects.equals(this.parser.commandName, "echo")) {
            this.echo();
        } else if (Objects.equals(this.parser.commandName, "pwd")) {
            this.pwd();
        } else if (Objects.equals(this.parser.commandName, "cd")) {
            this.cd();
        } else if (Objects.equals(this.parser.commandName, "ls")) {
            this.ls();
        } else if (Objects.equals(this.parser.commandName, "ls-r")) {
            this.ls_reversed();
        } else if (Objects.equals(this.parser.commandName, "mkdir")) {
            this.mkdir();
        } else if (Objects.equals(this.parser.commandName, "rm")) {
            if (this.parser.args.length != 1) {
                System.out.println("Wrong Path..!!");
            } else {
                this.rm();
            }
        } else if (Objects.equals(this.parser.commandName, "cat")) {
            if (this.parser.args.length != 2 && this.parser.args.length != 1) {
                System.out.println("need at least one file Path");
            } else {
                this.cat();
            }
        } else if (Objects.equals(this.parser.commandName, "history")) {
            this.getHistory();
        }
        else {
            System.out.println("Wrong command");
            history.remove(history.size()-1);
        }

        Arrays.fill(this.parser.args, null);
    }
}
