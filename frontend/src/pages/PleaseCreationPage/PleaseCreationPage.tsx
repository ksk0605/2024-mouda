import Button from '@_components/Button/Button';
import FormLayout from '@_layouts/FormLayout/FormLayout';
import LabeledInput from '@_components/Input/MoimInput';
import usePleaseInfoInput from './PleaseCreationPage.hook';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import ROUTES from '@_constants/routes';
import PLEASE_INPUT_INFOS from './PleaseCreationPage.constant';
import useAddPlease from '@_hooks/mutaions/useAddPlease';
import LabeledTextArea from '@_components/TextArea/LabeledTextArea';
import * as S from './PleaseCreationPage.style';

export default function PleaseCreationPage() {
  const navigate = useNavigate();
  const [isSubmitted, setIsSubmitted] = useState(false);
  const { mutate } = useAddPlease(() => {
    navigate(ROUTES.please);
  });

  const {
    inputData,
    handleInputChange,
    handleTextAreaChange,
    isValidPleaseInfoInput,
  } = usePleaseInfoInput();

  const handleRegisterButtonClick = async () => {
    if (!isValidPleaseInfoInput) {
      return;
    }
    if (isSubmitted) return;
    setIsSubmitted(true);
    mutate(inputData);
  };

  return (
    <FormLayout>
      <FormLayout.Header onBackArrowClick={() => navigate(ROUTES.main)}>
        해주세요 만들기
      </FormLayout.Header>

      <FormLayout.MainForm>
        <div css={S.inputContainer}>
          {PLEASE_INPUT_INFOS.map((info) =>
            info.type === 'textarea' ? (
              <LabeledTextArea
                name={info.name}
                title={info.title}
                key={info.title}
                required={info.required}
                placeholder={info.placeholder}
                onChange={handleTextAreaChange}
              />
            ) : (
              <LabeledInput
                name={info.name}
                title={info.title}
                key={info.title}
                required={info.required}
                placeholder={info.placeholder}
                onChange={handleInputChange}
              />
            ),
          )}
        </div>
      </FormLayout.MainForm>

      <FormLayout.BottomButtonWrapper>
        <Button
          shape="bar"
          onClick={handleRegisterButtonClick}
          disabled={!isValidPleaseInfoInput}
        >
          등록하기
        </Button>
      </FormLayout.BottomButtonWrapper>
    </FormLayout>
  );
}
