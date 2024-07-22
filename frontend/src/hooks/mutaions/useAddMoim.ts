import { useMutation, useQueryClient } from '@tanstack/react-query';

import QUERY_KEYS from '@_constants/queryKeys';
import { postMoim } from '@_apis/posts';

export default function useAddMoim(onSuccess: (moimId: number) => void) {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: postMoim,
    onSuccess: (moimId: number) => {
      queryClient.invalidateQueries({ queryKey: [QUERY_KEYS.moims] });

      onSuccess(moimId);
    },
  });
}