package elasticpot

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

    def put()
    {
        response.setContentType("text/plain")

        def requestLine = request.forwardURI
        println "PUT: " + requestLine
        render(requestLine)

        println "ElasticPot: Put Call: Index: " + params.index + " Object: " + params.object + " ID: " + params.id + " at time " + new Date()
    }


    def get()
    {

    }

    def post()
    {

    }

    def delete()
    {

    }
}

/*

BE1NR176:ElasticSearch a790100$ curl -XPUT 'http://localhost:9200/ews/Alert/5468215b0cf210243d8c3841' -d '{ "user" : "kimchy", "post_date" : "2007-11-15T14:12:12", "message" : "trying out Elasticsearch" }'
{"_index":"ews","_type":"Alert","_id":"5468215b0cf210243d8c3841","_version":4,"created":false}BE1NR176:ElasticSearch a790100$

 */