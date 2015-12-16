package elasticpot

import redis.clients.jedis.Jedis


class MainController
{


    /**
     *
     *
     *
     *
     *
     */


    def index()
    {

        println "ElasticPot: Index: Request from IP " + request.getRemoteAddr() + " at time " + new Date()

        response.setContentType("application/json")
        def data = " { \"status\" : 200,"+
                        "\"name\" : \"Flake\"," +
                        "\"cluster_name\" : \"elasticsearch\","+
                        "\"version\" : {"+
                        "\"number\" : \"1.4.1\","+
                        "\"build_hash\" : \"b88f43fc40b0bcd7f173a1f9ee2e97816de80b19\","+
                        "\"build_timestamp\" : \"2015-07-29T09:54:16Z\","+
                        "\"build_snapshot\" : false,"+
                        "\"lucene_version\" : \"4.10.4\"" +
                        "},"+
                        "\"tagline\" : \"You Know, for Search\"" +
                        "}"

        Jedis redisService = new Jedis("localhost")
        redisService.incr("requestCounter")
        redisService.lpush("Index Request",  "/" + "||" + new Date() + "||" + request.remoteAddr)

        render(data)
    }

    def put()
    {
        response.setContentType("text/plain")

        String requestLine = request.forwardURI
        if (request.queryString != null)
            requestLine += "?" + request.queryString

        def remoteIP = request.remoteAddr

        println "ElasticPot: Put Call: " + requestLine + " from IP: " + remoteIP + " at time " + new Date()

        // store request data

        Jedis redisService = new Jedis("localhost")

        redisService.incr("requestCounter")
        redisService.lpush("PUT Request",  requestLine + "||" + new Date() + "||" + request.remoteAddr)
        redisService.lpush("IP", request.remoteAddr + "|| " + new Date())

        render(requestLine)
    }


    def get()
    {

        Jedis redisService = new Jedis("localhost")

        response.setContentType("text/plain")

        String requestLine = request.forwardURI
        if (request.queryString != null)
            requestLine += "?" + request.queryString

        def remoteIP = request.remoteAddr

        println "ElasticPot: GET Call: " + requestLine + " from IP: " + remoteIP + " at time " + new Date()

        // store request data
        redisService.incr("requestCounter")
        redisService.lpush("GET Request",  requestLine + "||" + new Date() + "||" + request.remoteAddr)
        redisService.lpush("IP", request.remoteAddr + "|| " + new Date())


        if (requestLine.startsWith("/_search"))
        {

            // call home, if on a T-Pot installation
            EWSController x = new EWSController()
            x.send(remoteIP, requestLine, "MyElasticHoneypotHost")

            requestLine = "{\"took\":23,\"timed_out\":false,\"_shards\":{\"total\":10,\"successful\":10,\"failed\":0},\"hits\":{\"total\":19,\"max_score\":1.0,\"hits\":"
        }
        else {
            requestLine = "{\"error\":\"ElasticsearchIllegalArgumentException[No feature for name ["+ requestLine.substring(1) +"]]\",\"status\":400}"
        }


        render(requestLine)

    }

    def post()
    {

        Jedis redisService = new Jedis("localhost")

        response.setContentType("text/plain")

        String requestLine = request.forwardURI
        if (request.queryString != null)
            requestLine += "?" + request.queryString

        def remoteIP = request.remoteAddr

        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {

            println "ElasticPot: Parsing POST Call: " + requestLine + " from IP: " + remoteIP + " at time " + new Date() + " failed...."
            render(requestLine)
        }

        println "ElasticPot: POST Call: " + requestLine + " from IP: " + remoteIP + " at time " + new Date()
        if (jb.length() >= 2)
            println "            Body:     " + jb.toString()


        // store request data

        if (requestLine.startsWith("/_search"))
        {

            // call home, if on a T-Pot installation
            EWSController x = new EWSController()
            x.send(remoteIP, requestLine, "MyElasticHoneypotHost")

            requestLine = "{\"took\":23,\"timed_out\":false,\"_shards\":{\"total\":10,\"successful\":10,\"failed\":0},\"hits\":{\"total\":19,\"max_score\":1.0,\"hits\":"
        }
        else {
            requestLine = "{\"error\":\"ElasticsearchIllegalArgumentException[No feature for name ["+ requestLine.substring(1) +"]]\",\"status\":400}"
        }

        redisService.incr("requestCounter")
        redisService.lpush("POST Request",  requestLine + "||" + new Date() + "||" + request.remoteAddr)
        redisService.lpush("IP", request.remoteAddr + "|| " + new Date())

        render(requestLine)
    }

    def delete()
    {

        Jedis redisService = new Jedis("localhost")

        response.setContentType("text/plain")

        String requestLine = request.forwardURI
        if (request.queryString != null)
            requestLine += "?" + request.queryString

        def remoteIP = request.remoteAddr

        println "ElasticPot: Delete Call: " + requestLine + " from IP: " + remoteIP + " at time " + new Date()

        // store request data

        redisService.incr("requestCounter")
        redisService.lpush("DELETE Request",  requestLine + "||" + new Date() + "||" + request.remoteAddr)
        redisService.lpush("IP", request.remoteAddr + "|| " + new Date())

        render(requestLine)

    }
}

