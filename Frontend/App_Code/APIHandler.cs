
using System;
using System.IO;
using WDrive;

namespace API
{
    public static class APIHandler
    {
        
        private static WDriveApiService api = new WDriveApiService();
        public static bool currentlyLogged = false;
        public static bool refresh = false;
        public static bool pastebinFull = false;
        public static string pastingFile = "";
        public static bool movingAction = false;

        public static string exists(string filename)
        {
            return api.fileExists(filename);
        }

        public static string pasteFile()
        {
            return api.pasteFile();
        }

        public static string deleteFile(string filename)
        {
            return api.deleteFile(filename);
        }

        public static string copyFile(string filename)
        {
            return api.copyFile(filename);
        }

        public static string cutFile(string filename)
        {
            return api.cutFile(filename);
        }

        public static string accessDir(string dirname)
        {
            return api.accessDir(dirname);
        }

        public static string loadAccount(string UserName, string Password)
        {
            api = new WDriveApiService();
            refresh = false;
            pastingFile = "";
            currentlyLogged = false;
            pastebinFull = false;
            movingAction = false;
            return api.loadAccount(UserName, Password);
        }

        public static string createAccount(string UserName, string Password, int bytes)
        {
            return api.createAccount(UserName, Password, (long) bytes);
        }

        public static void generartxt(string msg)
        {
            string[] lines = { msg };

            // Set a variable to the My Documents path.
            string mydocpath =
                Environment.GetFolderPath(Environment.SpecialFolder.Desktop);

            // Write the string array to a new file named "WriteLines.txt".
            using (StreamWriter outputFile = new StreamWriter(mydocpath + @"\respuesta.txt"))
            {
                foreach (string line in lines)
                    outputFile.WriteLine(line);
            }
        }

        public static string getCurrentDir()
        {
            return api.getCurrentDirname();
        }

        public static string backDir()
        {
            if (!getCurrentDir().EndsWith(@"\drive"))
            {
                return api.accessDir("..");
            }

            return null;
        }

        public static string listFiles()
        {
            return api.listFiles();
        }

        public static string getTotalSpace()
        {
            return api.getTotalSpace();
        }

        public static string getAvailableSpace()
        {
            return api.getAvailableSpace();
        }

    }
}