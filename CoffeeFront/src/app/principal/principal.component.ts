import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.css']
})
export class PrincipalComponent {
  constructor(private route: ActivatedRoute, private router: Router){
    this.start()
  }

  async start(){
    var funcao = await localStorage.getItem("funcao")
    console.log(funcao)
    if(funcao == "ADMIN"){
      this.router.navigate(["admin"], {relativeTo: this.route});
    } else if(funcao == "GARCOM"){
      this.router.navigate(["garcom"], {relativeTo: this.route});
    } else if(funcao == "COZINHA"){
      this.router.navigate(["cozinha"], {relativeTo: this.route});
    }
  }

  isAdmin(){
    const permissao = localStorage.getItem("permissao");
    if(permissao == "ADMIN"){
      return true;
    }else{
      return false;
    }
  }

  isUserAuthenticated = (): boolean => {
    const token = localStorage.getItem("jwt");
    if (token){
      return true;
    }
    return false;
  }
  logOut = () => {
    localStorage.removeItem("jwt");
    this.router.navigate(["/login"]);
  }
}
