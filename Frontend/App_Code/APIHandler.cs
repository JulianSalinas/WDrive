
using System;
using System.IO;
using WDrive;

namespace API
{
    public class APIHandler
    {

        private WDriveApiService api;
        public bool currentlyLogged = false;

        public APIHandler() { api = new WDriveApiService(); }

        public string loadAccount(string UserName, string Password)
        {
            return api.loadAccount(UserName, Password);
        }

        public string createAccount(string UserName, string Password, int bytes)
        {
            return api.createAccount(UserName, Password, (long) bytes);
        }

        public void generartxt(string msg)
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

        public string getactual()
        {
            return api.getCurrentDirname();
        }

    }
}