import CommentCard from '@_components/CommentCard/CommentCard';
import * as S from '@_components/CommentList/ComentList.style';
import MessageInput from '@_components/Input/MessagInput/MessageInput';
import useWriteComment from '@_hooks/mutaions/useWriteComment';
import { Comment } from '@_types/index';
import { HTMLProps, useState } from 'react';

interface CommentListProps extends HTMLProps<HTMLDivElement> {
  moimId: number;
  comments: Comment[];
}

export default function CommentList(props: CommentListProps) {
  const { moimId, comments } = props;
  const { mutate: writeComment } = useWriteComment();
  const [selectedComment, setSelectedCommnet] = useState(0);

  return (
    <div css={S.commentListBox()}>
      {comments.map((comment) => {
        return (
          <CommentCard
            key={comment.commentId}
            comment={comment}
            onWriteClick={() => {
              if (comment.commentId === selectedComment) {
                setSelectedCommnet(0);
              } else {
                setSelectedCommnet(comment.commentId);
              }
            }}
            isChecked={comment.commentId === selectedComment}
          />
        );
      })}
      <MessageInput
        placeHolder={'메세지를 입력해주세요'}
        onSubmit={(message: string) =>
          writeComment({ moimId, message, selectedComment })
        }
      ></MessageInput>
    </div>
  );
}
