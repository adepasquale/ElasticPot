package elasticpot

import redis.clients.jedis.Jedis

class ReportController
{

    def index()
    {

        String prop = (String)grailsApplication.config['reportshow']

        if (!prop.contains(request.remoteAddr)) {
            redirect(controller:"Main", action: "get")
        }

        Jedis jedis = new Jedis("localhost")
        List reports = jedis.lrange("Index Request", 0, -1)

        String counter = jedis.get("requestCounter")

        List reportsReportHelper = new LinkedList()

        for (String item : reports) {

            def (request, date, ip) = item.tokenize( '||' )

            ReportHelper x = new ReportHelper(request: request, date: date, ip: ip)
            reportsReportHelper.add(x)

        }

        [Reports: reportsReportHelper, counter: counter]
    }

    def store()
    {

    }
}
