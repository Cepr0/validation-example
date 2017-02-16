package validationexample.controller;

import validationexample.AppConfig;
import validationexample.model.AjaxResponseBody;
import validationexample.model.SearchCriteria;
import validationexample.model.User;
import validationexample.services.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class SearchController {

    private final @NonNull UserService userService;

    private final @NonNull AppConfig appConfig;

    @PostMapping("/api/search")
    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody SearchCriteria search, Errors errors) {

        AjaxResponseBody result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);

        }

        List<User> users = userService.findByUserNameOrEmail(search.getUsername());
        if (users.isEmpty()) {
            result.setMsg("no user found! " + appConfig.getCount());
        } else {
            result.setMsg("success " + appConfig.getVal2());
        }
        result.setResult(users);

        return ResponseEntity.ok(result);

    }

}
