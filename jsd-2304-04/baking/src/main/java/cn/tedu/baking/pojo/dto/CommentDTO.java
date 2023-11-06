package cn.tedu.baking.pojo.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private String content;
    private Long userId;
    private Long contentId;

    public CommentDTO() {
        System.out.println("CommentDTO.CommentDTO");
    }
}
