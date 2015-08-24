package elasticpot

class MainController
{

    def index()
    {

        print "Request from IP " + request.getRemoteAddr() + " at time " + new Date()

        response.setContentType("application/json")
        def data = " { \"status\" : 200,"+
                        "\"name\" : \"Flake\"," +
                        "\"cluster_name\" : \"elasticsearch\","+
                        "\"version\" : {"+
                        "\"number\" : \"1.7.1\","+
                        "\"build_hash\" : \"b88f43fc40b0bcd7f173a1f9ee2e97816de80b19\","+
                        "\"build_timestamp\" : \"2015-07-29T09:54:16Z\","+
                        "\"build_snapshot\" : false,"+
                        "\"lucene_version\" : \"4.10.4\"" +
                        "},"+
                        "\"tagline\" : \"You Know, for Search\"" +
                        "}"
        render(data)
    }
}
