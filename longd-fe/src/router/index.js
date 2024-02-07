import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/views/HomeView.vue';
import ProfileView from '@/views/main/ProfileView.vue';
import GalleryListView from '@/views/gallery/GalleryListView.vue';
import GalleryDetailView from '@/views/gallery/GalleryDetailView.vue';
import CalendarView from '@/views/calendar/CalendarView.vue';
import ViduMainView from '@/views/openvidu/ViduMainView.vue';
import TestMapView from '@/views/map/TestMapView.vue';
import MapView from '@/views/map/MapView.vue';
import ClosedView from '@/views/main/ClosedView.vue';
import LoginSignUpView from '@/views/main/LoginSignUpView.vue';
import RequiredInfoView from '@/views/main/RequiredInfoView.vue';
import ConnectCodeView from '@/views/main/ConnectCodeView.vue';
import GalleryFolderView from '@/views/gallery/GalleryFolderView.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Home',
      component: HomeView,
    },
    {
      path: '/about',
      name: 'About',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue'),
    },
    {
      path: '/profile',
      name: 'Profile',
      component: ProfileView,
    },
    {
      path: '/gallery',
      name: 'GalleryFolder',
      component: GalleryFolderView,
    },
    {
      path: '/gallery/:folderName',
      name: 'GalleryList',
      component: GalleryListView,
    },
    {
      path: '/gallery/:folderName/:id',
      name: 'GalleryDetail',
      component: GalleryDetailView,
    },
    {
      path: '/calendar',
      name: 'Calendar',
      component: CalendarView,
    },
    {
      path: '/vidumain',
      name: 'ViduMain',
      component: ViduMainView,
    },
    {
      path: '/map',
      name: 'Map',
      component: MapView,
    },
    {
      path: '/testmap',
      name: 'TestMap',
      component: TestMapView,
    },
    {
      path: '/closed',
      name: 'Closed',
      component: ClosedView,
    },
    {
      path: '/login',
      name: 'Login',
      component: LoginSignUpView,
    },
    {
      path: '/requiredinfo',
      name: 'RequiredInfo',
      component: RequiredInfoView,
    },
    {
      path: '/connectcode',
      name: 'ConnectCode',
      component: ConnectCodeView,
    },
  ],
});

export default router;
