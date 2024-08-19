package mouda.backend.comment.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import mouda.backend.comment.domain.Comment;
import mouda.backend.darakbangmember.domain.DarakbangMember;
import mouda.backend.moim.domain.Moim;

public record CommentCreateRequest(
	Long parentId,

	@NotNull
	String content
) {
	public Comment toEntity(Moim moim, DarakbangMember member) {
		return Comment.builder()
			.content(content)
			.moim(moim)
			.member(member)
			.createdAt(LocalDateTime.now())
			.parentId(parentId)
			.build();
	}
}
