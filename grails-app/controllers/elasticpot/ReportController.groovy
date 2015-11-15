package elasticpot

import redis.clients.jedis.Jedis


class ReportController
{

    def index()
    {
        Jedis redisService = new Jedis("localhost")

        String prop = (String)grailsApplication.config['reportshow']

        if (!prop.contains(request.remoteAddr)) {
            redirect(controller:"Main", action: "get")
        }

        List reports = redisService.lrange("Index Request", 0, -1)

        String counter = redisService.get("requestCounter")

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
