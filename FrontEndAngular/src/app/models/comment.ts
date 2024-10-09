export class Comment {
  id: number;
  content: string;
  timestamp: Date;
  visiteur_id: number;
  recipe_id: number;

  constructor(
    id: number,
    content: string,
    timestamp: Date,
    visiteur_id: number,
    recipe_id: number,

  ) {
    this.id = id;
    this.content = content;
    this.timestamp = timestamp;
    this.visiteur_id = visiteur_id;
    this.recipe_id = recipe_id;

  }
}
