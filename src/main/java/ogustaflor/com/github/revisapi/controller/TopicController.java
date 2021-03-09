package ogustaflor.com.github.revisapi.controller;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.topic.Topic;
import ogustaflor.com.github.revisapi.entity.topic.TopicDTO;
import ogustaflor.com.github.revisapi.service.TopicService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/topics")
public class TopicController extends AbstractRestController<Topic, TopicService, TopicDTO.Request.TopicStore, TopicDTO.Request.TopicUpdate> {
}
