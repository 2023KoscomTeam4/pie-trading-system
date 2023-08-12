<template>
    <nav>
            <v-list>
                <img src="../assets/koscom.jpg"
                        width="200"
                        />
            </v-list>
            <v-list flat>
                <router-link to="/home">
                    
                    <v-list-item>
                        <v-list-item-content>
                            <i class="fas fa-house-user"></i>
                            <v-list-item-subtitle align="center">메인화면</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                
                </router-link>
                <v-list-item router to="/portfolio">
                    <v-list-item-content>
                        <v-icon class="mb-2">fas fa-search</v-icon> 
                        <v-list-item-subtitle align="center">포트폴리오</v-list-item-subtitle>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item router to="/myPage">
                    <v-list-item-content>
                        <v-icon class="mb-2">fas fa-list</v-icon>
                        <v-list-item-subtitle align="center">마이페이지</v-list-item-subtitle>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
            <router-link to="/createroom">
                    <v-list-item>
                        <v-list-item-content>
                            <i class="fas fa-home-user"></i>
                            <v-list-item-subtitle align="center">방 만들기</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                
            </router-link>
            

        </nav>
</template>
<script>
export default {
    components: { 
    },
    data: () => ({
        drawer: true
    })
}
</script>