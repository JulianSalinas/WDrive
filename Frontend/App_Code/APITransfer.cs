using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using API;

namespace APIT
{
    public static class APITransfer
    {
        private static APIHandler api;

        public static void sendAPI(APIHandler apiR)
        {
            api = apiR;
        }

        public static APIHandler getAPI()
        {
            return api;
        }
    }
}