import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import GalleryListView from '@/views/gallery/GalleryListView.vue';
import GalleryDetailView from '@/views/gallery/GalleryDetailView.vue';
import GalleryCreateView from '@/views/gallery/GalleryCreateView.vue';
import MapView from '../views/map/MapView.vue';
import ClosedView from '../views/main/ClosedView.vue';
import LoginSignUpView from '../views/main/LoginSignUpView.vue';
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
      path: '/map/',
      name: 'Map',
      component: MapView,
    },
    {
      path: '/closed/',
      name: 'Closed',
      component: ClosedView,
    },
    {
      path: '/login/',
      name: 'Login',
      component: LoginSignUpView,
    },
  ],
});

export default router;
