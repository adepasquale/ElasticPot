package elasticpot

import redis.clients.jedis.*


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

        Jedis jedis = new Jedis("localhost")
        jedis.incr("requestCounter")
        jedis.lpush("Index Request",  "/" + "||" + new Date() + "||" + request.remoteAddr)

        render(data)
    }

    def put()
    {
        response.setContentType("text/plain")

        def requestLine = request.forwardURI
        def remoteIP = request.remoteAddr

        println "ElasticPot: Put Call: " + requestLine + " from IP: " + remoteIP + " at time " + new Date()

        // store request data

        Jedis jedis = new Jedis("localhost")
        jedis.incr("requestCounter")
        jedis.lpush("PUT Request",  requestLine + "||" + new Date() + "||" + request.remoteAddr)
        jedis.lpush("IP", request.remoteAddr + "|| " + new Date())

        render(requestLine)
    }


    def get()
    {

        response.setContentType("text/plain")

        def requestLine = request.forwardURI
        def remoteIP = request.remoteAddr

        println "ElasticPot: GET Call: " + requestLine + " from IP: " + remoteIP + " at time " + new Date()

        // store request data

        Jedis jedis = new Jedis("localhost")
        jedis.incr("requestCounter")
        jedis.lpush("GET Request",  requestLine + "||" + new Date() + "||" + request.remoteAddr)
        jedis.lpush("IP", request.remoteAddr + "|| " + new Date())


        if (requestLine.startsWith("/_search"))
            requestLine = "{\"took\":23,\"timed_out\":false,\"_shards\":{\"total\":10,\"successful\":10,\"failed\":0},\"hits\":{\"total\":19,\"max_score\":1.0,\"hits\":"

        render(requestLine)

    }

    def post()
    {
        response.setContentType("text/plain")

        def requestLine = request.forwardURI
        def remoteIP = request.remoteAddr

        println "ElasticPot: POST Call: " + requestLine + " from IP: " + remoteIP + " at time " + new Date()

        // store request data

        Jedis jedis = new Jedis("localhost")
        jedis.incr("requestCounter")
        jedis.lpush("POST Request",  requestLine + "||" + new Date() + "||" + request.remoteAddr)
        jedis.lpush("IP", request.remoteAddr + "|| " + new Date())

        render(requestLine)
    }

    def delete()
    {

        response.setContentType("text/plain")

        def requestLine = request.forwardURI
        def remoteIP = request.remoteAddr

        println "ElasticPot: Delete Call: " + requestLine + " from IP: " + remoteIP + " at time " + new Date()

        // store request data

        Jedis jedis = new Jedis("localhost")
        jedis.incr("requestCounter")
        jedis.lpush("DELETE Request",  requestLine + "||" + new Date() + "||" + request.remoteAddr)
        jedis.lpush("IP", request.remoteAddr + "|| " + new Date())

        render(requestLine)

    }
}

