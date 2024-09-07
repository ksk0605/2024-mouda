import POLICIES from '@_constants/poclies';

export const validateTitle = (title: string) =>
  POLICIES.minimumTitleLength <= title.length &&
  title.length <= POLICIES.maximumTitleLength;

export const validateDate = (date: string) => {
  if (date === '') {
    return true;
  }
  const nowDate = new Date();
  const nowDateYyyymmdd = `${nowDate.getFullYear()}-${(nowDate.getMonth() + 1).toString().padStart(2, '00')}-${nowDate.getDate().toString().padStart(2, '00')}`;
  return date >= nowDateYyyymmdd && POLICIES.yyyymmddDashRegex.test(date);
};

export const validateTime = (time: string) => {
  if (time === '') {
    return true;
  }
  if (!POLICIES.hhmmRegex.test(time)) {
    return false;
  }

  const now = new Date();
  const [inputHour, inputMinute] = time.split(':').map(Number);
  const inputTime = new Date(
    now.getFullYear(),
    now.getMonth(),
    now.getDate(),
    inputHour,
    inputMinute,
  );

  if (inputTime < now) {
    return false;
  }
  return true;
};

export const validatePlace = (place: string) => {
  if (place === '') {
    return true;
  }
  return (
    POLICIES.minimumPlaceLength <= place.length &&
    place.length <= POLICIES.maximumPlaceLength
  );
};
export const validateMaxPeople = (maxPeople: number | string) => {
  if (typeof maxPeople === 'string') {
    maxPeople = Number(maxPeople); // string을 number로 변환
    if (isNaN(maxPeople)) {
      return false; // 변환이 실패하면 false 반환
    }
  }

  return (
    POLICIES.minimumMaxPeople <= maxPeople &&
    maxPeople <= POLICIES.maximumMaxPeople
  );
};

export const validateAuthorNickname = (authorNickname: string) =>
  POLICIES.minimumAuthorNicknameLength <= authorNickname.length &&
  authorNickname.length <= POLICIES.maximumAuthorNicknameLength;
