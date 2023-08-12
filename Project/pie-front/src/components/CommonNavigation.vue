<template>
    <nav>
        <v-navigation-drawer permanent v-model="drawer" dark app mini-variant mini-variant-width="100" class="orange darken-1">
            <v-list  style="position: absolute; top:0" class="ml-3" flat>
                <v-img
                  :src="require('../assets/koscom.jpg')"
                  width="80"
                />
            </v-list>
            
            <v-list>
                <v-list-item class="mb-3">
                </v-list-item>
            </v-list>

            <v-list flat>
                <v-list-item router to="/dash-board">
                    <v-list-item-content>
                        <v-icon class="mb-2">fas fa-dashboard</v-icon>
                        <v-list-item-subtitle>대시보드</v-list-item-subtitle>
                    </v-list-item-content>
                </v-list-item>

                <v-list-item router to="/room-list">
                        <v-list-item-content>
                        <v-icon class="mb-2">fas fa-list</v-icon>
                            <v-list-item-subtitle>방 목록</v-list-item-subtitle>
                        </v-list-item-content>
                </v-list-item>

                <v-list-item router to="/room-create">
                        <v-list-item-content>
                        <v-icon class="mb-2">fas fa-search</v-icon>
                            <v-list-item-subtitle>방 생성</v-list-item-subtitle>
                        </v-list-item-content>
                </v-list-item>

                <v-list-item router to="/portfolio">
                        <v-list-item-content>
                            <v-icon class="mb-2">fas fa-dropbox</v-icon> 
                            <v-list-item-subtitle>포트폴리오</v-list-item-subtitle>
                            </v-list-item-content>
                </v-list-item>
                
                <v-list-item router to="/my-page">
                        <v-list-item-content>
                        <v-icon class="mb-2">fas fa-person</v-icon>
                            <v-list-item-subtitle>마이페이지</v-list-item-subtitle>
                        </v-list-item-content>
                </v-list-item>
            </v-list>
                
                <v-list style="position: absolute; bottom:0" class="ml-3" flat>
                    <v-list-item router to="/">
                        <v-list-item-action>
                            <v-icon right>fas fa-sign-out-alt</v-icon>
                        </v-list-item-action>
                    </v-list-item>
                </v-list>
            </v-navigation-drawer>
        </nav>
</template>
<script>
export default {
    data: () => ({
        drawer: true
    })
}
</script>