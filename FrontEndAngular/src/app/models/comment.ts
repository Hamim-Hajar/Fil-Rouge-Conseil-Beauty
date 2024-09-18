export class Comment {
  id: number;
  content: string;
  timestamp: Date;
  visiteurId: number;  // Store the ID of Visiteur (relationship)
  recipeId: number;    // Store the ID of Recipe (relationship)

  constructor(
    id: number,
    content: string,
    timestamp: Date,
    visiteurId: number,
    recipeId: number
  ) {
    this.id = id;
    this.content = content;
    this.timestamp = timestamp;
    this.visiteurId = visiteurId;
    this.recipeId = recipeId;
  }
}
