package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenges")
@CrossOrigin(origins = "http://localhost:5173")
public class ChallengeController {
    private ChallengeService challengeService;
    public ChallengeController(ChallengeService challengeService){
        this.challengeService=challengeService;
    }
@GetMapping
    public List<Challenge> getChallenges() {
        return challengeService.getallChallenges();
    }
@PostMapping
    public String addChallenges(@RequestBody Challenge challenge) {
        boolean challenges = challengeService.addchallenges(challenge);
       if(challenges)
           return "Challenge Added Successfully";
       else
           return "Challenge not Added Successfully";
    }
    @GetMapping("/{month}")
    public ResponseEntity<List<Challenge>> getmonthChallenge(@PathVariable String month)
    {
        List<Challenge> chall=challengeService.getMonthChallenges(month);
        if(!chall.isEmpty())
            return new ResponseEntity<>(chall, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> UpdateChallenge(@PathVariable Long id,@RequestBody Challenge updatedchallenge)
    {
        boolean isChallengeUpdated=challengeService.updateChallenge(id,updatedchallenge);
        if(isChallengeUpdated)
            return new ResponseEntity<>("Challenge Updated Successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteChallenge(@PathVariable Long id)
    {
        boolean isChallengeDeleted=challengeService.deleteChallenge(id);
        if(isChallengeDeleted)
            return new ResponseEntity<>("Challenge Deleted Successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
