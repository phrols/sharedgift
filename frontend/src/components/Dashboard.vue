<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { FamilyAdminAPI, type FamilyAdmin } from '@/api/family-admin-api'

const me = ref<FamilyAdmin>({email:''})
const api = new FamilyAdminAPI(); 

const getUserInfo = async () => {
    me.value = await api.getConnectedFamilyAdmin();
}

onMounted(() => {
  getUserInfo();
})

</script>

<template>
  <div class="dashboard">
    <h2 class="blue">Bonjour {{ me.name }} !</h2>
</div>
</template>

<style scoped>

</style>
