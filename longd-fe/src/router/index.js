import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import GalleryListView from '@/views/gallery/GalleryListView.vue';
import GalleryDetailView from '@/views/gallery/GalleryDetailView.vue';
import GalleryCreateView from '@/views/gallery/GalleryCreateView.vue';
import CalendarView from '@/views/calendar/CalendarView.vue';
import CalendarView2 from '@/views/calendar/CalendarView2.vue';
import ViduMainView from '@/views/openvidu/ViduMainView.vue';
import TestMapView from '@/views/map/TestMapView.vue';
import MapView from '@/views/map/MapView.vue';
import ClosedView from '@/views/main/ClosedView.vue';
import LoginSignUpView from '@/views/main/LoginSignUpView.vue';
import RequiredInfoView from '@/views/main/RequiredInfoView.vue';
import ConnectCodeView from '@/views/main/ConnectCodeView.vue';

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
      path: '/gallery',
      name: 'GalleryList',
      component: GalleryListView,
    },
    {
      path: '/gallery/:id',
      name: 'GalleryDetail',
      component: GalleryDetailView,
    },
    {
      path: '/gallery/create',
      name: 'GalleryCreate',
      component: GalleryCreateView,
    },
    {
      path: '/calendar',
      name: 'Calendar',
      component: CalendarView,
    },
    {
      path: '/calendar2',
      name: 'Calendar2',
      component: CalendarView2,
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
