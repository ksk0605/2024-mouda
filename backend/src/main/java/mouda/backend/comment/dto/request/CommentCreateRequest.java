package mouda.backend.comment.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import mouda.backend.comment.domain.Comment;
import mouda.backend.member.domain.Member;
import mouda.backend.moim.domain.Moim;

public record CommentCreateRequest(
	Long parentId,

	@NotNull
	String content
) {
	public Comment toEntity(Moim moim, Member member) {
		return Comment.builder()
			.content(content)
			.moim(moim)
			.member(member)
			.createdAt(LocalDateTime.now())
			.parentId(parentId)
			.build();
	}
}
