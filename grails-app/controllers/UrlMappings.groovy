class UrlMappings {

    static mappings = {

        "/"(controller: "Main", action:"index")
        "/report"(controller: "Report", action:"index")
        "/home"(controller: "EWS", action: "index")

        "/**" (controller: "Main") {
            action = [GET: "get", PUT: "put", DELETE: "delete", POST: "post"]
        }

    }
}
