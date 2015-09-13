class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "Main", action:"index")
        "/report"(controller: "Report", action:"index")

        "/_search" (controller: "Main") {
            action = [GET: "get", PUT: "put", DELETE: "delete", POST: "post"]
        }

        "/$index/$object/$id" (controller: "Main") {
            action = [GET: "get", PUT: "put", DELETE: "delete", POST: "post"]
        }


	}
}
