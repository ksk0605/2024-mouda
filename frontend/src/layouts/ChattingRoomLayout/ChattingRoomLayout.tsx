import * as S from './ChattingRoomLayout.style';

import ChattingRoomFooter from './ChattingRoomFooter/ChattingRoomFooter';
import { PropsWithChildren } from 'react';
import StickyTriSectionHeader from '@_layouts/components/StickyTriSectionHeader/StickyTriSectionHeader';

function ChattingRoomLayout(props: PropsWithChildren) {
  const { children } = props;

  return <div css={S.layoutStyle}>{children}</div>;
}

ChattingRoomLayout.Header = StickyTriSectionHeader;
ChattingRoomLayout.Footer = ChattingRoomFooter;

export default ChattingRoomLayout;