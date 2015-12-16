package elasticpot

import JavaHelpers.EWSHelper

import java.text.SimpleDateFormat

class EWSController
{

    def userName
    def password
    def url

    /**
     * if a T-Pot (see dtag-dev-sec.github.io) installation is found, use this credentials to pass data back
     * to DTAG early warning system
     * @return
     */
    def getCredentialsDefaultConfig()
    {

        String username = null , password = null, server = null
        String line

        File f = new File("/data/ews/conf/ews.cfg")
        if(f.exists() && !f.isDirectory()) {

            def inFile = new File("/data/ews/conf/ews.cfg")
            inFile.withReader { reader ->
                while (line = reader.readLine()) {

                    if (line.startsWith("username = ")) {
                        username = line.substring(11)
                    }
                    else if (line.startsWith("token = ")) {
                        password = line.substring(8)
                    }
                    else if (line.startsWith("rhost_first = ")) {
                        server = line.substring(14)
                    }
                }


                }

            return [username, password, server]

        }

        else {
            return [null, null, null]
        }

    }

    /**
     * send data to core EWS system of DTAG
     * @param attackerIP
     * @param attackerRequest
     * @param host
     * @return
     */
    def send(String attackerIP, String attackerRequest, String host) {

        def (String username, String password, String server) = getCredentialsDefaultConfig()

        if (username != null && password != null && server != null)
        {

            Date curDate = new Date()
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            //         <CreateTime tz="+0200">2015-09-09 16:39:21</CreateTime>


            String DateToStr = format.format(curDate)

            EWSHelper.sendAlarm(username, password, DateToStr, attackerIP, attackerRequest, host, server)
        }

    }



}
