import { Fragment, useState } from 'react';
import MoimTabBar, { MainPageTab } from '@_components/MoimTabBar/MoimTabBar';
import HomeLayout from '@_layouts/HomeLayout.tsx/HomeLayout';
import HomeMainContent from '@_components/HomeMainContent/HomeMainContent';
import NavigationBar from '@_components/NavigationBar/NavigationBar';
import NavigationBarWrapper from '@_layouts/components/NavigationBarWrapper/NavigationBarWrapper';
import ROUTES from '@_constants/routes';
import { useNavigate } from 'react-router-dom';
import HomeHeaderContent from '@_components/HomeHeaderContent/HomHeaderContent';
import PlusButton from '@_components/PlusButton/PlusButton';

export default function MainPage() {
  const navigate = useNavigate();

  const [currentTab, setCurrentTab] = useState<MainPageTab>('모임목록');

  const handleTabClick = (tab: MainPageTab) => {
    setCurrentTab(tab);
  };

  return (
    <Fragment>
      <HomeLayout>
        <HomeLayout.Header>
          <HomeHeaderContent>우아한테크코스</HomeHeaderContent>
          <MoimTabBar currentTab={currentTab} onTabClick={handleTabClick} />
        </HomeLayout.Header>

        <HomeLayout.Main>
          <HomeMainContent currentTab={currentTab} />
        </HomeLayout.Main>

        <HomeLayout.HomeFixedButtonWrapper>
          <PlusButton onClick={() => navigate(ROUTES.addMoim)} />
        </HomeLayout.HomeFixedButtonWrapper>
      </HomeLayout>

      <NavigationBarWrapper>
        <NavigationBar />
      </NavigationBarWrapper>
    </Fragment>
  );
}
