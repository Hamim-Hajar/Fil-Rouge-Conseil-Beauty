
export class Article {
  id: number;
  titre: string;
  contenu: string;
  datePublication: Date; // Use Date for LocalDateTime equivalent
  image?: string; // Optional, as it is nullable in your entity
  specialist_id: number; // Representing the relation with Specialist

  constructor(
    id: number,
    titre: string,
    contenu: string,
    datePublication: Date,
    image?: string,
    specialist_id?: number
  ) {
    this.id = id;
    this.titre = titre;
    this.contenu = contenu;
    this.datePublication = datePublication;
    this.image = image || ''; // Optional handling
    this.specialist_id = specialist_id || 0; // Optional handling
  }
}
