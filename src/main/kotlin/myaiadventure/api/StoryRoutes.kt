package myaiadventure.api

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

@Serializable
data class CreateStoryRequest(
    val title: String
)

fun Route.storyRoutes(
    storyService: StoryService
) { route("/api/stories") {

        get("/{storyId}") {
            val storyId = call.parameters["storyId"]?:return@get call.respond(HttpStatusCode.BadRequest, "storyId is required")

            val story = storyService.getStory(storyId)

            if (story == null) {
                return@get call.respond(
                    HttpStatusCode.NotFound,
                    "story is required"
                )
            }
            call.respond(story)
        }

        post {
            val request = call.receive<CreateStoryRequest>()

            val story = storyService.createStory(request.title)

            call.respond(
                HttpStatusCode.Created,
                story
            )
        }
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
        }
    }

}