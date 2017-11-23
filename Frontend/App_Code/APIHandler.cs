
using System;
using System.IO;
using WDrive;

namespace API
{
    public static class APIHandler
    {

        private static WDriveApiService api = new WDriveApiService();
        public static bool currentlyLogged = false;

        public static string accessDir(string dirname)
        {
            return api.accessDir(dirname);
        }

        public static string loadAccount(string UserName, string Password)
        {
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

        public static void backDir()
        {
            if (!getCurrentDir().EndsWith(@"\drive"))
            {
                api.accessDir("..");
            }
        }

        public static string listFiles()
        {
            return api.listFiles();
        }
    }
}