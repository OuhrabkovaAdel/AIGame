package myaiadventure.api

import com.example.myaiadventure.api.CreateStoryRequest
import com.example.myaiadventure.api.PlayerAction
import myaiadventure.service.StoryService
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlinx.serialization.Serializable

fun Route.storyRoutes(
    storyService: StoryService
) { route("/api/stories") {

        post {
        val request = call.receive<CreateStoryRequest>()

        val response = storyService.createStory(request)

        call.respond(
        HttpStatusCode.Created,
            response
        )
}

       get("/{storyId}") {
            val storyId = call.parameters["storyId"]
                ?: return@get call.respond(
                    HttpStatusCode.BadRequest,
                    "Missing storyId"
                )
            val response = storyService.getStory(storyId)

            call.respond(
                HttpStatusCode.OK,
                response
            )
        }
 /*
        post("/{storyId}/actions") {

            val storyId = call.parameters["storyId"]
                ?: return@post call.respond(
                    HttpStatusCode.BadRequest,
                    "Missing storyId"
                )

            val request = call.receive<PlayerAction>()

            val turn = storyService.processAction(
                storyId = storyId,
                action = request.action
            )
            call.respond(turn)
        }*/
    }

}