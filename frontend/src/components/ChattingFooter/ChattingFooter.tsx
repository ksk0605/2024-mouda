import { ChangeEvent, FormEvent, useRef, useState } from 'react';
import {
  footer,
  menuButton,
  messageForm,
  messageInput,
  sendingButton,
} from './ChattingFooter.style';

import POLICES from '@_constants/poclies';
import Plus from '@_common/assets/plus.svg';
import SendButton from '@_components/Icons/SendButton';
import X from '@_common/assets/x.svg';
import { useTheme } from '@emotion/react';

interface ChattingFooterProps {
  onMenuClick: () => void;
  onSubmit: (message: string) => void;
}

export default function ChattingFooter(props: ChattingFooterProps) {
  const { onSubmit, onMenuClick } = props;
  const [isMenuClicked, setIsMenuClicked] = useState(false);
  const [message, setMessage] = useState('');
  const theme = useTheme();
  const input = useRef<HTMLInputElement | null>(null);

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

      <form
        onSubmit={(e: FormEvent<HTMLFormElement>) => {
          e.preventDefault();
          onSubmit(message);
          setMessage('');
          input.current?.focus();
        }}
        css={messageForm({ theme })}
      >
        <input
          css={messageInput({ theme })}
          type="text"
          placeholder="메시지를 입력하세요"
          maxLength={POLICES.maxMessageLength}
          value={message}
          onChange={(e: ChangeEvent<HTMLInputElement>) =>
            setMessage(e.target.value)
          }
          required
          ref={input}
        />
        <button css={sendingButton} type="submit" disabled={message === ''}>
          <SendButton disabled={message === ''} />
        </button>
      </form>
    </div>
  );
}
