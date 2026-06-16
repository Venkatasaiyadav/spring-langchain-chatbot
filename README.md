# Spring Boot LangChain4j PGVector RAG Chatbot

A support chatbot built with Spring Boot, LangChain4j, OpenRouter, and PGVector.  
It loads a company PDF, splits it into chunks, stores embeddings in PostgreSQL with pgvector, and answers user questions using Retrieval-Augmented Generation (RAG).

## Features.

- Spring Boot REST API
- LangChain4j integration
- OpenRouter chat + embedding models
- PDF document ingestion
- Chunking and embedding generation
- PGVector for vector storage
- RAG-based question answering
- Postman-friendly API testing

## Tech Stack

- Java 21
- Spring Boot
- LangChain4j
- PostgreSQL + pgvector
- Docker
- OpenRouter
- Maven

## Project Flow

### 1. Ingestion flow
- Load PDF from `src/main/resources/docs/`
- Parse PDF text
- Split text into chunks
- Generate embeddings for each chunk
- Store embeddings in PGVector

### 2. Chat flow
- Receive user question through REST API
- Generate embedding for the user query
- Search PGVector for similar chunks
- Inject relevant chunks into the prompt
- Send prompt to LLM through OpenRouter
- Return grounded response

## API Endpoint

### POST `/api/chat`

Request body:

```json
{
  "message": "What does the company policy say about leave?"
}
```

Example response:

```json
{
  "answer": "Employees are entitled to annual leave according to the company policy..."
}
```

## Local Setup

### 1. Clone the repository

```bash
git clone https://github.com/your-username/springboot-langchain4j-pgvector-rag-chatbot.git
cd springboot-langchain4j-pgvector-rag-chatbot
```

### 2. Start PostgreSQL with pgvector

```bash
docker run -d --name pgvector-db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=ragdb -p 5432:5432 ankane/pgvector
```

Enable the extension:

```bash
docker exec -it pgvector-db psql -U postgres -d ragdb -c "CREATE EXTENSION IF NOT EXISTS vector;"
```

### 3. Configure environment variables

#### PowerShell

```powershell
$env:OPENAI_API_KEY="your-openrouter-api-key"
$env:OPENAI_BASE_URL="https://openrouter.ai/api/v1"
$env:OPENAI_CHAT_MODEL="openai/gpt-4o-mini"
$env:OPENAI_EMBEDDING_MODEL="text-embedding-3-small"

$env:DB_URL="jdbc:postgresql://localhost:5432/ragdb"
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="postgres"

$env:PGVECTOR_HOST="localhost"
$env:PGVECTOR_PORT="5432"
$env:PGVECTOR_DATABASE="ragdb"
$env:PGVECTOR_USER="postgres"
$env:PGVECTOR_PASSWORD="postgres"
$env:PGVECTOR_TABLE="document_embeddings"
```

### 4. Add the PDF

Place your company PDF inside:

```text
src/main/resources/docs/company-policy.pdf
```

### 5. Run the application

```bash
mvn spring-boot:run
```

## Postman Testing

### URL
```text
http://localhost:8080/api/chat
```

### Method
```text
POST
```

### Headers
```text
Content-Type: application/json
```

### Body
```json
{
  "message": "What are the work from home rules?"
}
```

## Environment Variables

The application uses environment variables for secrets and configuration.

| Variable | Description |
|---------|-------------|
| `OPENAI_API_KEY` | OpenRouter API key |
| `OPENAI_BASE_URL` | OpenRouter base URL |
| `OPENAI_CHAT_MODEL` | Chat model name |
| `OPENAI_EMBEDDING_MODEL` | Embedding model name |
| `DB_URL` | PostgreSQL JDBC URL |
| `DB_USERNAME` | PostgreSQL username |
| `DB_PASSWORD` | PostgreSQL password |
| `PGVECTOR_HOST` | PGVector host |
| `PGVECTOR_PORT` | PGVector port |
| `PGVECTOR_DATABASE` | Database name |
| `PGVECTOR_USER` | Database user |
| `PGVECTOR_PASSWORD` | Database password |
| `PGVECTOR_TABLE` | Table used for embeddings |

## Suggested `.gitignore`

```gitignore
target/
.idea/
.vscode/
*.log
.DS_Store
.env
src/main/resources/application-local.yml
```

## Important Notes

- Never commit real API keys to GitHub.
- Keep secrets in environment variables or local-only config files.
- If a real API key was already committed, rotate it immediately.
- The first startup may take longer because the PDF is parsed and embedded.

## Future Improvements

- Multi-document ingestion
- Metadata-based filtering
- Source citations in responses
- Chat memory
- Admin endpoint for re-indexing
- Docker Compose setup
- Authentication and role-based access

## Learning Outcome

This project demonstrates how to build a real-world Java RAG application using:
- Spring Boot for REST APIs
- LangChain4j for LLM and RAG orchestration
- OpenRouter for model access
- PGVector for semantic search
- PDF ingestion for company knowledge

## License

This project is for learning and portfolio use.
