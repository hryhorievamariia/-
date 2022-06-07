package com.example.deanery1.service;


import com.example.deanery1.model.Group;
import com.example.deanery1.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public List<Group> getAll(){return groupRepository.findAll();}
    public Group getById(int id){
        return groupRepository.getById(id);
    }
    public Group save(Group group){
        return groupRepository.save(group);
    }
    public void deleteById(int id){
        groupRepository.deleteById(id);
    }

    public void updateById(int id, Group group){
        Group group1 = groupRepository.getById(id);
        group1.setId(group.getId());
        group1.setName(group.getName());
        group1.setDescription(group.getDescription());
        group1.setImageUrl(group.getImageUrl());
        groupRepository.save(group1);

    }


    public boolean findById(int categoryId) {
        return groupRepository.findById(categoryId).isPresent();
    }
}
