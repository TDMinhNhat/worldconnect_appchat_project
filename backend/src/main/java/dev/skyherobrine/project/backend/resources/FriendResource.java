package dev.skyherobrine.project.backend.resources;

import dev.skyherobrine.project.backend.dtos.FriendDTO;
import dev.skyherobrine.project.backend.models.Response;
import dev.skyherobrine.project.backend.models.mongodb.Friend;
import dev.skyherobrine.project.backend.repositories.mongodb.FriendRepository;
import dev.skyherobrine.project.backend.services.FriendService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/friends")
@Slf4j
public class FriendResource {

    private final FriendRepository friendRepository;
    private final FriendService friendService;

    public FriendResource(FriendRepository friendRepository, FriendService friendService) {
        this.friendRepository = friendRepository;
        this.friendService = friendService;
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addFriend(@Valid @RequestBody FriendDTO request) {
        log.info("Friend: call the api add friend");
        Friend friend = friendService.addFriend(request);
        log.info("Friend: add friend successfully");
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Friend added successfully",
                friend
        ));
    }

    @PutMapping("/update_status")
    public ResponseEntity<Response> updateStatusFriend(@Valid @RequestBody FriendDTO.FriendStatusDTO request) {
        log.info("Friend: call the api update status friend");
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Update status friend successfully",
                friendService.updateStatusFriend(request)
        ));
    }

    @GetMapping("/get_all_friends")
    public ResponseEntity<Response> getAllFriend(@RequestParam Long userId) {
        log.info("Friend: call the api get all friends");
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Get all friends successfully",
                friendRepository.findBySender_IdOrRecipient_IdOrderByIdAsc(userId, userId)
        ));
    }
}
