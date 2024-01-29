import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import GalleryListView from '@/views/gallery/GalleryListView.vue';
import GalleryDetailView from '@/views/gallery/GalleryDetailView.vue';
import GalleryCreateView from '@/views/gallery/GalleryCreateView.vue';
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
  ],
});

export default router;
