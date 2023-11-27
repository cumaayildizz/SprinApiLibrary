package com.cuma.yildiz.LibrarySpring.api;


import com.cuma.yildiz.LibrarySpring.business.abstracts.IPublisherService;
import com.cuma.yildiz.LibrarySpring.core.config.modelMapper.IModelMapperService;
import com.cuma.yildiz.LibrarySpring.core.result.Result;
import com.cuma.yildiz.LibrarySpring.core.result.ResultData;
import com.cuma.yildiz.LibrarySpring.core.utils.ResultHelper;
import com.cuma.yildiz.LibrarySpring.dto.request.publisher.PublisherSaveRequest;
import com.cuma.yildiz.LibrarySpring.dto.request.publisher.PublisherUpdateRequest;
import com.cuma.yildiz.LibrarySpring.dto.response.CursorResponse;
import com.cuma.yildiz.LibrarySpring.dto.response.author.AuthorResponse;
import com.cuma.yildiz.LibrarySpring.dto.response.publisher.PublisherResponse;
import com.cuma.yildiz.LibrarySpring.entities.Author;
import com.cuma.yildiz.LibrarySpring.entities.Publisher;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/librarySpring/publishers")
public class PublisherController {

    private final IPublisherService publisherService;
    private final IModelMapperService modelMapper;

    public PublisherController(IPublisherService publisherService, IModelMapperService modelMapper) {
        this.publisherService = publisherService;
        this.modelMapper = modelMapper;
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<PublisherResponse> save(@Valid @RequestBody PublisherSaveRequest publisherSaveRequest){
        Publisher savePublisher = this.modelMapper.forRequest().map(publisherSaveRequest , Publisher.class);
        this.publisherService.save(savePublisher);

        return ResultHelper.created(this.modelMapper.forResponse().map(savePublisher , PublisherResponse.class));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> update(@Valid @RequestBody PublisherUpdateRequest publisherUpdateRequest){
        Publisher updatePublisher = this.modelMapper.forRequest().map(publisherUpdateRequest , Publisher.class);
        this.publisherService.update(updatePublisher);

        return ResultHelper.success(this.modelMapper.forResponse().map(updatePublisher, PublisherResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> get(@PathVariable("id") int id){
        Publisher publisher = this.publisherService.get(id);
        PublisherResponse publisherResponse = this.modelMapper.forResponse().map(publisher , PublisherResponse.class);
        return ResultHelper.success(publisherResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<PublisherResponse>> cursor(
            @RequestParam(name = "page" , required = false , defaultValue = "0") int page ,
            @RequestParam(name = "pageSize" , required = false , defaultValue = "2") int pageSize)
    {
        Page<Publisher> publisherPage = this.publisherService.cursor(page , pageSize);
        Page<PublisherResponse> publisherResponsePage = publisherPage
                .map(publisher -> this.modelMapper.forResponse()
                        .map(publisher , PublisherResponse.class));

        return ResultHelper.cursorMethod(publisherResponsePage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id){
        this.publisherService.delete(id);
        return ResultHelper.Ok();
    }


}
