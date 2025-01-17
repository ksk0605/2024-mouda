import { ChangeEvent, useRef, useState } from 'react';
import {
  footer,
  menuButton,
  messageForm,
  messageTextArea,
  sendingButton,
} from './ChattingFooter.style';

import POLICES from '@_constants/poclies';
import Plus from '@_common/assets/plus.svg';
import SendButton from '@_components/Icons/SendButton';
import X from '@_common/assets/x.svg';
import { common } from '@_common/common.style';
import { useTheme } from '@emotion/react';

interface ChattingFooterProps {
  onMenuClick: () => void;
  disabled: boolean;
  onSubmit: (message: string) => void;
}

export default function ChattingFooter(props: ChattingFooterProps) {
  const { onSubmit, disabled, onMenuClick } = props;
  const [isMenuClicked, setIsMenuClicked] = useState(false);
  const [message, setMessage] = useState('');
  const theme = useTheme();
  const textArea = useRef<HTMLTextAreaElement | null>(null);

  return (
    <div css={footer({ theme })}>
      {/* TODO: 현재 Button이 유연하지 않아 html 태그를 사용
         필요한 점: 테마 적용(백그라운드 컬러 설정 어려움)+disabled를 optional로 주기 */}
      <button
        css={menuButton({ theme })}
        onClick={() => {
          onMenuClick();
          setIsMenuClicked(!isMenuClicked);
        }}
      >
        {isMenuClicked ? <X /> : <Plus />}
      </button>

      <div css={messageForm({ theme })}>
        <textarea
          css={messageTextArea({ theme })}
          placeholder="메시지를 입력하세요"
          maxLength={POLICES.maxMessageLength}
          onChange={(e: ChangeEvent<HTMLTextAreaElement>) =>
            setMessage(e.target.value)
          }
          rows={1}
          required
          ref={textArea}
        />
        <button
          css={[sendingButton, common.nonScroll]}
          disabled={message === ''}
          onClick={(e) => {
            e.preventDefault();
            onSubmit(message);
            setMessage('');
            if (textArea.current) textArea.current.value = '';
            textArea.current?.focus();
          }}
        >
          <SendButton disabled={message === '' || disabled} />
        </button>
      </div>
    </div>
  );
}
