package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {
    private Long nextid = 1L;
//    private List<Challenge> challenges = new ArrayList<>();
    @Autowired
    ChallengeRepository challengeRepository;
    public ChallengeService() {

    }

    public List<Challenge> getallChallenges() {
        return challengeRepository.findAll();
    }

    public boolean addchallenges(Challenge challenge) {
        if (challenge != null) {
            challenge.setId(nextid++);
            challengeRepository.save(challenge);
            return true;
        } else return false;
    }

    public List<Challenge> getMonthChallenges(String month) {

        Optional<List<Challenge>> challenge=challengeRepository.findByMonthIgnoreCase(month);
        return challenge.orElse(null);
    }

    public boolean updateChallenge(Long id, Challenge updatedchallenge) {
        Optional<Challenge> challenge=challengeRepository.findById(id);
        if(challenge.isPresent())
        {
            Challenge challengetoUpdate=challenge.get();
            challengetoUpdate.setMonth(updatedchallenge.getMonth());
            challengetoUpdate.setDescription(updatedchallenge.getDescription());
            challengeRepository.save(challengetoUpdate);
            return true;
        }


        return false;
    }

    public boolean deleteChallenge(Long id) {
        Optional<Challenge> challenge=challengeRepository.findById(id);
        if(challenge.isPresent()) {
            challengeRepository.deleteById(id);
            return true;
        }
        return false;
        }

}
