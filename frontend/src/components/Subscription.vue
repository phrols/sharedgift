<script setup lang="ts">

</script>

<template>
  <div class="subscription">
    <h2>Inscrivez-vous dès maintenant pour pouvoir partager vos cadeaux !</h2>
    <form>
      <div>
        <label for="name">Nom</label>
        <input type="text" id="name" name="name" required>
      </div>
      <div>
        <label for="email">Email</label>
        <input type="email" id="email" name="email" required>        
      </div>
      <div>
        <label for="password">Mot de passe</label>
        <input type="password" id="password" name="password" required>
      </div>
      <div>
        <button type="submit">Je m'inscris</button>
      </div>
    </form>
</div>
</template>

<style scoped>
.subscription {
  margin: 0 auto;
  max-width: 400px;
  display: flex;
  flex-direction: column;
}
</style>
