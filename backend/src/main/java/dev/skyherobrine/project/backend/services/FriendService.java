package dev.skyherobrine.project.backend.services;

import dev.skyherobrine.project.backend.dtos.FriendDTO;
import dev.skyherobrine.project.backend.enums.FriendStatus;
import dev.skyherobrine.project.backend.models.mariadb.User;
import dev.skyherobrine.project.backend.models.mongodb.Friend;
import dev.skyherobrine.project.backend.repositories.mariadb.UserRepository;
import dev.skyherobrine.project.backend.repositories.mongodb.FriendRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    public FriendService(FriendRepository friendRepository, UserRepository userRepository) {
        this.friendRepository = friendRepository;
        this.userRepository = userRepository;
    }

    public Friend addFriend(FriendDTO request) {
        log.info("Friend: cal the service add friend");
        User sender = userRepository.findById(request.getSenderId()).orElseThrow(() -> new EntityNotFoundException("The sender id = " + request.getSenderId() + " wasn't found!"));
        log.info("Friend: find the sender");
        User receiver = userRepository.findById(request.getReceiverId()).orElseThrow(() -> new EntityNotFoundException("The receiver id = " + request.getReceiverId() + " wasn't found!"));
        log.info("Friend: find the receiver");

        Friend friend = new Friend(createMaxId(), sender, receiver);
        Friend result = friendRepository.save(friend);
        log.info("Friend: save the friend successfully");

        return result;
    }

    public Friend updateStatusFriend(FriendDTO.FriendStatusDTO request) {
        log.info("Friend: call the service update status friend");
        Friend friend = friendRepository.findBySender_IdAndRecipient_Id(request.getFirstUserId(), request.getSecondUserId()).orElse(
                friendRepository.findBySender_IdAndRecipient_Id(request.getSecondUserId(), request.getFirstUserId()).orElseThrow(() -> new EntityNotFoundException("There is no friend relationship between these two users"))
        );
        log.info("Friend: find the friend relationship");

        friend.setStatus(FriendStatus.valueOf(request.getStatus()));
        Friend result = friendRepository.save(friend);

        log.info("Friend: update the status friend successfully");
        return result;
    }

    private Long createMaxId() {
        log.info("Friend: call the service create max id");
        Long maxId = friendRepository.getMaxCurrentId().orElse(0L) + 1;
        log.info("Friend: create the max id = " + maxId);
        return maxId;
    }
}
